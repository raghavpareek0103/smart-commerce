package com.raghav.ecommerce.service;

import com.raghav.ecommerce.model.VerificationCode;

public interface VerificationService {

    VerificationCode createVerificationCode(String otp, String email);
}
