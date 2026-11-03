package com.raghav.ecommerce.ai.services;

import com.raghav.ecommerce.exception.ProductException;
import com.raghav.ecommerce.response.ApiResponse;

public interface AiChatBotService {

    ApiResponse aiChatBot(String prompt,Long productId,Long userId) throws ProductException;
}
