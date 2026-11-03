package com.raghav.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {



}
