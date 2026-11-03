package com.raghav.ecommerce.response;

import com.raghav.ecommerce.dto.OrderHistory;
import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse {
    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;
}
