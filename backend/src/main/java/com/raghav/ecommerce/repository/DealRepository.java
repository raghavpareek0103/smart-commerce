package com.raghav.ecommerce.repository;

import com.raghav.ecommerce.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal,Long> {

}
