package com.raghav.ecommerce.ai.services;

import com.raghav.ecommerce.exception.ProductException;
import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.CartItem;
import com.raghav.ecommerce.model.Order;
import com.raghav.ecommerce.model.OrderItem;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.repository.CartRepository;
import com.raghav.ecommerce.repository.OrderRepository;
import com.raghav.ecommerce.repository.ProductRepository;
import com.raghav.ecommerce.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Shopzy AI assistant.
 * 1) Database se context banata hai (product / cart / orders / kuch products ki list)
 * 2) Gemini ko bhejta hai (busy ho to retry + doosra model)
 * 3) Koi bhi error aaye to app crash nahi hoti — user ko saaf message milta hai
 */
@Service
@RequiredArgsConstructor
public class AiChatBotServiceImpl implements AiChatBotService {

    // application.properties se aate hain
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    // Comma se alag kai models: pehla busy ho to agla try hota hai
    @Value("${gemini.model:gemini-3.8-flash,gemini-3.6-flash,gemini-3.5-flash-lite}")
    private String geminiModel;

    @Value("${gemini.base-url:https://generativelanguage.googleapis.com}")
    private String geminiBaseUrl;

    // Ye errors thodi der baad theek ho jaate hain (busy / limit / server issue)
    private static final java.util.Set<Integer> RETRYABLE = java.util.Set.of(429, 500, 502, 503, 504);

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse aiChatBot(String prompt, Long productId, Long userId) throws ProductException {
        ApiResponse res = new ApiResponse();

        if (geminiApiKey == null || geminiApiKey.isBlank() || geminiApiKey.startsWith("your_")) {
            res.setMessage("AI assistant is not configured yet. Please add your Gemini API key in application.properties (gemini.api.key).");
            res.setStatus(false);
            return res;
        }

        try {
            String context = buildContext(productId, userId);
            String reply = callGeminiWithRetry(prompt, context);
            res.setMessage(reply);
            res.setStatus(true);
        } catch (HttpStatusCodeException e) {
            int code = e.getStatusCode().value();
            System.out.println("Gemini error " + code + " : " + e.getResponseBodyAsString());
            if (code == 400 || code == 401 || code == 403) {
                res.setMessage("The AI service rejected the request (" + code + "). Please check the Gemini API key in application.properties.");
            } else {
                res.setMessage("Our AI assistant is a bit busy right now (" + code + "). Please try again in a few seconds.");
            }
            res.setStatus(false);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage("Sorry, something went wrong on my side. Please try again.");
            res.setStatus(false);
        }
        return res;
    }

    // ---------------- Context (store ka data) ----------------

    private String buildContext(Long productId, Long userId) {
        StringBuilder sb = new StringBuilder();

        // 1) Jis product ke page se chat khula
        if (productId != null) {
            productRepository.findById(productId).ifPresent(p -> {
                sb.append("CURRENT PRODUCT the customer is looking at:\n").append(describeProduct(p)).append("\n\n");
            });
        }

        // 2) Logged-in user ka cart aur orders
        if (userId != null) {
            Cart cart = cartRepository.findByUserId(userId);
            if (cart != null && cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
                sb.append("CUSTOMER'S CART (total ₹").append((int) cart.getTotalSellingPrice())
                        .append(", ").append(cart.getTotalItem()).append(" items):\n");
                for (CartItem item : cart.getCartItems()) {
                    Product p = item.getProduct();
                    sb.append("- ").append(p != null ? p.getTitle() : "Product")
                            .append(" | size ").append(item.getSize())
                            .append(" | qty ").append(item.getQuantity())
                            .append(" | ₹").append(item.getSellingPrice()).append("\n");
                }
                sb.append("\n");
            } else {
                sb.append("CUSTOMER'S CART: empty\n\n");
            }

            List<Order> orders = orderRepository.findByUserId(userId);
            if (orders != null && !orders.isEmpty()) {
                sb.append("CUSTOMER'S RECENT ORDERS:\n");
                orders.stream()
                        .sorted(java.util.Comparator.comparing(Order::getOrderDate,
                                java.util.Comparator.nullsLast(java.util.Comparator.reverseOrder())))
                        .limit(5)
                        .forEach(o -> {
                            sb.append("- Order ").append(o.getOrderId() != null ? o.getOrderId() : o.getId())
                                    .append(" | status ").append(o.getOrderStatus())
                                    .append(" | ₹").append(o.getTotalSellingPrice())
                                    .append(" | ordered ").append(o.getOrderDate() != null ? o.getOrderDate().toLocalDate() : "-")
                                    .append(" | expected delivery ").append(o.getDeliverDate() != null ? o.getDeliverDate().toLocalDate() : "-")
                                    .append(" | items: ");
                            for (OrderItem oi : o.getOrderItems() != null ? o.getOrderItems() : List.<OrderItem>of()) {
                                sb.append(oi.getProduct() != null ? oi.getProduct().getTitle() : "item")
                                        .append(" x").append(oi.getQuantity()).append("; ");
                            }
                            sb.append("\n");
                        });
                sb.append("\n");
            } else {
                sb.append("CUSTOMER'S ORDERS: no orders yet\n\n");
            }
        } else {
            sb.append("The customer is NOT logged in, so cart and order details are not available. ")
                    .append("If they ask about their cart or orders, politely ask them to log in first.\n\n");
        }

        // 3) Suggestions ke liye kuch products
        List<Product> catalog = productRepository.findAll(PageRequest.of(0, 25)).getContent();
        if (!catalog.isEmpty()) {
            sb.append("SOME PRODUCTS AVAILABLE IN THE STORE (use these for suggestions):\n");
            for (Product p : catalog) {
                sb.append("- ").append(p.getTitle())
                        .append(" | ₹").append(p.getSellingPrice())
                        .append(" (MRP ₹").append(p.getMrpPrice()).append(", ").append(p.getDiscountPercent()).append("% off)")
                        .append(p.getCategory() != null ? " | category " + p.getCategory().getCategoryId() : "")
                        .append("\n");
            }
        }
        return sb.toString();
    }

    private String describeProduct(Product p) {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(p.getTitle()).append("\n");
        if (p.getSeller() != null && p.getSeller().getBusinessDetails() != null) {
            sb.append("Brand/Seller: ").append(p.getSeller().getBusinessDetails().getBusinessName()).append("\n");
        }
        sb.append("Price: ₹").append(p.getSellingPrice())
                .append(" (MRP ₹").append(p.getMrpPrice()).append(", ").append(p.getDiscountPercent()).append("% off)\n");
        sb.append("Color: ").append(p.getColor()).append("\n");
        sb.append("Sizes: ").append(p.getSizes()).append("\n");
        sb.append("In stock quantity: ").append(p.getQuantity()).append("\n");
        sb.append("Ratings count: ").append(p.getNumRatings()).append("\n");
        if (p.getCategory() != null) sb.append("Category: ").append(p.getCategory().getCategoryId()).append("\n");
        sb.append("Description: ").append(p.getDescription()).append("\n");
        return sb.toString();
    }

    // ---------------- Gemini call ----------------

    /**
     * Busy (503) / limit (429) aane par: 1 baar ruk ke dobara try,
     * phir bhi na chale to list ka agla model. 404 (model nahi mila) par seedha agla model.
     * 400/401/403 (key ya request ki galti) par turant error.
     */
    private String callGeminiWithRetry(String prompt, String context) {
        HttpStatusCodeException last = null;
        for (String m : geminiModel.split(",")) {
            String model = m.trim();
            if (model.isEmpty()) continue;
            for (int attempt = 1; attempt <= 2; attempt++) {
                try {
                    return callGemini(model, prompt, context);
                } catch (HttpStatusCodeException e) {
                    last = e;
                    int code = e.getStatusCode().value();
                    System.out.println("Gemini [" + model + "] attempt " + attempt + " -> " + code + " : " + e.getResponseBodyAsString());
                    if (code == 404) break;                  // ye model nahi hai -> agla model
                    if (!RETRYABLE.contains(code)) throw e;  // key / request ki galti -> retry ka fayda nahi
                    if (attempt == 1) {
                        try { Thread.sleep(1200); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                    }
                }
            }
        }
        if (last != null) throw last;
        throw new IllegalStateException("No Gemini model configured (gemini.model)");
    }

    private String callGemini(String model, String prompt, String context) {
        String url = geminiBaseUrl + "/v1beta/models/" + model + ":generateContent";

        String systemText =
                "You are 'Shopzy AI Assistant', a friendly shopping assistant for the Shopzy e-commerce store in India.\n" +
                        "- Reply in the same language the customer uses (English, Hindi or Hinglish).\n" +
                        "- Keep answers short, helpful and friendly (2-6 short lines). Use ₹ for prices.\n" +
                        "- Only use the store data given below for facts about products, cart and orders. Never invent order details.\n" +
                        "- If something is not in the data, say you don't have that information.\n" +
                        "- For greetings like 'hi', greet back and say what you can help with.\n\n" +
                        "STORE DATA:\n" + context;

        JSONObject body = new JSONObject()
                .put("systemInstruction", new JSONObject()
                        .put("parts", new JSONArray().put(new JSONObject().put("text", systemText))))
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("role", "user")
                                .put("parts", new JSONArray().put(new JSONObject().put("text", prompt)))))
                .put("generationConfig", new JSONObject()
                        .put("temperature", 0.6)
                        .put("maxOutputTokens", 800));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", geminiApiKey);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<>(body.toString(), headers), String.class);

        JSONObject json = new JSONObject(response.getBody());
        JSONArray candidates = json.optJSONArray("candidates");
        if (candidates == null || candidates.length() == 0) {
            return "Sorry, I couldn't come up with an answer for that. Could you rephrase?";
        }
        JSONArray parts = candidates.getJSONObject(0).optJSONObject("content") != null
                ? candidates.getJSONObject(0).getJSONObject("content").optJSONArray("parts")
                : null;
        if (parts == null) {
            return "Sorry, I couldn't come up with an answer for that. Could you rephrase?";
        }

        StringBuilder text = new StringBuilder();
        for (int i = 0; i < parts.length(); i++) {
            text.append(parts.getJSONObject(i).optString("text", ""));
        }
        String reply = text.toString().trim();
        return reply.isEmpty() ? "Sorry, I couldn't come up with an answer for that. Could you rephrase?" : reply;
    }
}