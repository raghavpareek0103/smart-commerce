package com.raghav.ecommerce.ai.controllers;

import com.raghav.ecommerce.ai.services.AiChatBotService;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.request.Prompt;
import com.raghav.ecommerce.response.ApiResponse;
import com.raghav.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/chat")
public class AiChatBotController {

    private final AiChatBotService aiChatBotService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse> generate(
            @RequestBody Prompt prompt,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long productId,
            @RequestHeader(required = false, name = "Authorization") String jwt) throws Exception {

        String message = prompt.getPrompt();

        // Login ho to user nikalo, warna guest ki tarah chalao (error mat do)
        Long currentUserId = null;
        if (jwt != null && jwt.startsWith("Bearer ") && jwt.length() > 20) {
            try {
                User user = userService.findUserProfileByJwt(jwt);
                currentUserId = user.getId();
            } catch (Exception e) {
                System.out.println("AI chat: invalid/expired token, continuing as guest");
            }
        }

        ApiResponse apiResponse = aiChatBotService.aiChatBot(message, productId, currentUserId);
        return ResponseEntity.ok(apiResponse);
    }
}