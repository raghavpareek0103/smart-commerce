package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.Coupon;
import com.raghav.ecommerce.model.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon createCoupon(Coupon coupon);
    void deleteCoupon(Long couponId);
    List<Coupon> getAllCoupons();
    
    Coupon getCouponById(Long couponId);
}
