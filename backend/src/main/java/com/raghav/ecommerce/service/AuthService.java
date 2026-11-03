package com.raghav.ecommerce.service;

import com.raghav.ecommerce.exception.SellerException;
import com.raghav.ecommerce.exception.UserException;
import com.raghav.ecommerce.request.LoginRequest;
import com.raghav.ecommerce.request.SignupRequest;
import com.raghav.ecommerce.response.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthService {

    void sentLoginOtp(String email) throws UserException, MessagingException;
    String createUser(SignupRequest req) throws SellerException;
    AuthResponse signin(LoginRequest req) throws SellerException;

}
