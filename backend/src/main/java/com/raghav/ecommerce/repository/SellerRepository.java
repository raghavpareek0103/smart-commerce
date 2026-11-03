package com.raghav.ecommerce.repository;

import com.raghav.ecommerce.domain.AccountStatus;
import com.raghav.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus status);
}
