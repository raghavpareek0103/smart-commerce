package com.raghav.ecommerce.service.impl;

import com.raghav.ecommerce.config.JwtProvider;
import com.raghav.ecommerce.domain.USER_ROLE;
import com.raghav.ecommerce.exception.SellerException;
import com.raghav.ecommerce.exception.UserException;
import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.model.VerificationCode;
import com.raghav.ecommerce.repository.CartRepository;
import com.raghav.ecommerce.repository.UserRepository;
import com.raghav.ecommerce.repository.VerificationCodeRepository;
import com.raghav.ecommerce.request.LoginRequest;
import com.raghav.ecommerce.request.SignupRequest;
import com.raghav.ecommerce.response.AuthResponse;
import com.raghav.ecommerce.service.AuthService;
import com.raghav.ecommerce.service.EmailService;
import com.raghav.ecommerce.service.UserService;
import com.raghav.ecommerce.utils.OtpUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    private final CustomeUserServiceImplementation customUserDetails;
    private final CartRepository cartRepository;


    @Override
    public void sentLoginOtp(String email)
            throws UserException, MessagingException {

        System.out.println("=================================");
        System.out.println("OTP REQUEST RECEIVED");
        System.out.println("EMAIL: " + email);
        System.out.println("=================================");

        String SIGNING_PREFIX = "signing_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            System.out.println("SIGNING PREFIX REMOVED");
            System.out.println("ACTUAL EMAIL: " + email);

            userService.findUserByEmail(email);
        }

        // Check existing OTP
        VerificationCode existingCode =
                verificationCodeRepository.findByEmail(email);

        if (existingCode != null) {

            System.out.println("OLD OTP FOUND - DELETING");

            verificationCodeRepository.delete(existingCode);
        }

        // Generate new OTP
        String otp = OtpUtils.generateOTP();

        System.out.println("NEW OTP GENERATED: " + otp);

        // Save OTP in database
        VerificationCode verificationCode = new VerificationCode();

        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        verificationCodeRepository.save(verificationCode);

        System.out.println("OTP SAVED IN DATABASE");

        // Email details
        String subject = "Shopzy Login/Signup OTP";

        String text =
                "Your Shopzy verification OTP is: ";

        System.out.println("SENDING OTP EMAIL...");

        // Send email
        try {
            emailService.sendVerificationOtpEmail(email, otp, subject, text);
        } catch (Exception e) {
            System.out.println("EMAIL FAILED. USE THIS OTP: " + otp);
        }

        System.out.println("OTP PROCESS COMPLETED");
    }
    @Override
    public String createUser(SignupRequest req) throws SellerException {

        String email = req.getEmail();

        String fullName = req.getFullName();

        String otp = req.getOtp();

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new SellerException("wrong otp...");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {

            User createdUser = new User();
            createdUser.setEmail(email);
            createdUser.setFullName(fullName);
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("9083476123");
            createdUser.setPassword(passwordEncoder.encode(otp));

            System.out.println(createdUser);

            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }


        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(
                USER_ROLE.ROLE_CUSTOMER.toString()));


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signin(LoginRequest req) throws SellerException {

        String username = req.getEmail();
        String otp = req.getOtp();

        System.out.println(username + " ----- " + otp);

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();


        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return authResponse;

    }



    private Authentication authenticate(String username, String otp) throws SellerException {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null ");
            throw new BadCredentialsException("Invalid username or password");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new SellerException("wrong otp...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
