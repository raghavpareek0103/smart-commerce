package com.raghav.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String username);

}
