package com.raghav.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}
