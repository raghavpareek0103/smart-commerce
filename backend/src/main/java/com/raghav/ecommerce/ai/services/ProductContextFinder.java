package com.raghav.ecommerce.ai.services;

import com.raghav.ecommerce.model.Category;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.repository.ProductRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User ke sawaal se (jaise "best phone under 30k") DATABASE me se sahi products dhoondhta hai,
 * taaki Gemini ko wahi products diye jaayein jo sawaal se match karte hain.
 *
 * - "phone / mobile / smartphone" jaise shabdon ko category se match karta hai
 * - "under 30k", "below ₹999", "within 20,000" se budget nikalta hai
 * - Max 8 products, text format me (Gemini ke prompt me daalne ke liye)
 */
@Component
@RequiredArgsConstructor
public class ProductContextFinder {

    private final ProductRepository productRepository;

    private static final int LIMIT = 8;

    // Aam bol-chaal ke shabd -> DB me search karne wale shabd (title / category)
    private static final Map<String, List<String>> SYNONYMS = new LinkedHashMap<>();
    static {
        SYNONYMS.put("phone", List.of("mobile", "phone", "smartphone"));
        SYNONYMS.put("mobile", List.of("mobile", "phone", "smartphone"));
        SYNONYMS.put("smartphone", List.of("mobile", "phone", "smartphone"));
        SYNONYMS.put("iphone", List.of("iphone", "apple"));
        SYNONYMS.put("laptop", List.of("laptop", "notebook"));
        SYNONYMS.put("watch", List.of("watch"));
        SYNONYMS.put("smartwatch", List.of("smartwatch", "smart_watch", "watch"));
        SYNONYMS.put("headphone", List.of("headphone", "earbud", "headset", "earphone"));
        SYNONYMS.put("earbud", List.of("earbud", "headphone", "earphone"));
        SYNONYMS.put("speaker", List.of("speaker"));
        SYNONYMS.put("tv", List.of("television", "tv"));
        SYNONYMS.put("television", List.of("television", "tv"));
        SYNONYMS.put("camera", List.of("camera"));
        SYNONYMS.put("tablet", List.of("tablet", "ipad"));
        SYNONYMS.put("shoe", List.of("shoe", "sneaker", "footwear"));
        SYNONYMS.put("sneaker", List.of("sneaker", "shoe"));
        SYNONYMS.put("kurta", List.of("kurta"));
        SYNONYMS.put("saree", List.of("saree", "sari"));
        SYNONYMS.put("shirt", List.of("shirt"));
        SYNONYMS.put("tshirt", List.of("t-shirt", "tshirt", "t_shirt"));
        SYNONYMS.put("jeans", List.of("jeans"));
        SYNONYMS.put("jacket", List.of("jacket"));
        SYNONYMS.put("dress", List.of("dress"));
        SYNONYMS.put("lamp", List.of("lamp", "light"));
        SYNONYMS.put("bedsheet", List.of("bedsheet", "bed_linen", "bed sheet"));
        SYNONYMS.put("decor", List.of("decor", "home_decor"));
        SYNONYMS.put("wedding", List.of("sherwani", "kurta", "festive", "lehenga", "ethnic"));
        SYNONYMS.put("office", List.of("formal", "shirt", "trouser", "blazer"));
        SYNONYMS.put("gift", List.of("watch", "perfume", "wallet", "decor", "earbud"));
    }

    private static final Set<String> STOP_WORDS = Set.of(
            "best", "good", "under", "below", "within", "show", "some", "want", "need", "with",
            "for", "the", "and", "from", "price", "rupees", "budget", "suggest", "please", "which",
            "what", "buy", "me", "a", "an", "of", "in", "to", "is", "are", "i", "my", "upto", "up",
            "less", "than", "cheap", "product", "products", "this", "that", "latest", "top", "kya", "koi", "acha", "accha", "batao", "hai"
    );

    /** Sawaal se match hone wale products (Gemini ko dene ke liye text). Kuch na mile to "" */
    public String findRelevantProducts(String question) {
        List<Product> products = search(question);
        if (products.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Product p : products) {
            sb.append("- ").append(p.getTitle())
                    .append(" | Price: ₹").append(p.getSellingPrice())
                    .append(" (MRP ₹").append(p.getMrpPrice())
                    .append(", ").append(p.getDiscountPercent()).append("% off)")
                    .append(" | Colour: ").append(p.getColor())
                    .append(" | Product ID: ").append(p.getId());
            if (p.getCategory() != null) sb.append(" | Category: ").append(p.getCategory().getName());
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<Product> search(String question) {
        if (question == null || question.isBlank()) return List.of();
        // Controller product page se "the product id is 12, ..." jodta hai — use budget mat samjho
        String q = question.toLowerCase(Locale.ROOT).replaceAll("the product id is \\d+,?", " ");

        Integer budget = extractBudget(q);
        List<String> terms = extractTerms(q);
        if (terms.isEmpty() && budget == null) return List.of();

        Specification<Product> spec = (root, query, cb) -> {
            List<Predicate> and = new ArrayList<>();

            if (!terms.isEmpty()) {
                Join<Product, Category> cat = root.join("category", JoinType.LEFT);
                Join<Category, Category> parent = cat.join("parentCategory", JoinType.LEFT);
                List<Predicate> or = new ArrayList<>();
                for (String t : terms) {
                    String like = "%" + t + "%";
                    or.add(cb.like(cb.lower(root.get("title")), like));
                    or.add(cb.like(cb.lower(cat.get("categoryId")), like));
                    or.add(cb.like(cb.lower(cat.get("name")), like));
                    or.add(cb.like(cb.lower(parent.get("categoryId")), like));
                }
                and.add(cb.or(or.toArray(new Predicate[0])));
            }
            if (budget != null) {
                and.add(cb.lessThanOrEqualTo(root.get("sellingPrice"), budget));
            }
            return cb.and(and.toArray(new Predicate[0]));
        };

        // Budget diya ho to budget ke andar sabse achhe (mehnge) pehle, warna sabse zyada ratings wale
        Sort sort = budget != null
                ? Sort.by(Sort.Direction.DESC, "sellingPrice")
                : Sort.by(Sort.Direction.DESC, "numRatings");

        return productRepository.findAll(spec, PageRequest.of(0, LIMIT, sort)).getContent();
    }

    /** "under 30k", "below ₹999", "within 20,000", "30 thousand" -> number */
    static Integer extractBudget(String q) {
        Matcher m = Pattern
                .compile("(?:under|below|within|upto|up to|less than|max|<|budget)?\\s*(?:₹|rs\\.?|inr)?\\s*(\\d[\\d,]*(?:\\.\\d+)?)\\s*(k|thousand|lakh|lac)?\\b")
                .matcher(q);
        Integer best = null;
        while (m.find()) {
            String num = m.group(1).replace(",", "");
            double v;
            try { v = Double.parseDouble(num); } catch (NumberFormatException e) { continue; }
            String unit = m.group(2);
            if (unit != null) {
                if (unit.startsWith("k") || unit.startsWith("thousand")) v *= 1000;
                else v *= 100000;
            }
            // RAM / GB jaise chhote numbers ko budget mat samjho
            if (v < 99) continue;
            best = (int) Math.round(v);
        }
        return best;
    }

    static List<String> extractTerms(String q) {
        LinkedHashSet<String> terms = new LinkedHashSet<>();
        String cleaned = java.text.Normalizer.normalize(q, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")            // décor -> decor
                .replaceAll("[^a-z0-9\\s-]", " ");
        for (String w : cleaned.split("\\s+")) {
            if (w.isBlank()) continue;
            String singular = w.endsWith("s") && w.length() > 3 ? w.substring(0, w.length() - 1) : w;
            if (SYNONYMS.containsKey(w)) terms.addAll(SYNONYMS.get(w));
            else if (SYNONYMS.containsKey(singular)) terms.addAll(SYNONYMS.get(singular));
        }
        if (terms.isEmpty()) {
            // Koi jaana-pehchana shabd nahi mila -> sawaal ke bade shabd hi search karo
            for (String w : cleaned.split("\\s+")) {
                if (w.length() >= 4 && !STOP_WORDS.contains(w) && !w.matches("\\d+k?")) terms.add(w);
            }
        }
        return new ArrayList<>(terms);
    }
}