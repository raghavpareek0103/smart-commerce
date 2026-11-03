package com.raghav.ecommerce.repository;

import com.raghav.ecommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCode(String couponCode);
}
