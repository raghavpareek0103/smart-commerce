package com.raghav.ecommerce.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order>findByUserId(Long userId);
    List<Order> findBySellerIdOrderByOrderDateDesc(Long sellerId);
    List<Order> findBySellerIdAndOrderDateBetween(Long sellerId,LocalDateTime startDate, LocalDateTime endDate);

}
