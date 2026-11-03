package com.raghav.ecommerce.service;

import com.raghav.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductDiscountTest {

    @Test
    @DisplayName("MRP aur selling price se sahi discount % nikalta hai")
    void discount_isCalculated() {
        assertThat(ProductServiceImpl.calculateDiscountPercentage(1000, 700)).isEqualTo(30);
        assertThat(ProductServiceImpl.calculateDiscountPercentage(2169, 1299)).isEqualTo(40);
        assertThat(ProductServiceImpl.calculateDiscountPercentage(999, 999)).isZero();
    }

    @Test
    @DisplayName("MRP 0 ya minus ho to error aata hai")
    void discount_invalidMrp_throws() {
        assertThatThrownBy(() -> ProductServiceImpl.calculateDiscountPercentage(0, 100))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ProductServiceImpl.calculateDiscountPercentage(-50, 10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}