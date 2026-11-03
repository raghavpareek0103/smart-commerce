package com.raghav.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
