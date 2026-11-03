package com.raghav.ecommerce.repository;

import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);


}
