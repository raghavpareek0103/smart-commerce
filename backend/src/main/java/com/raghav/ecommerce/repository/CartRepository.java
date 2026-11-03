package com.raghav.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	 Cart findByUserId(Long userId);
}
