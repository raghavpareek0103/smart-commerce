package com.raghav.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghav.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
