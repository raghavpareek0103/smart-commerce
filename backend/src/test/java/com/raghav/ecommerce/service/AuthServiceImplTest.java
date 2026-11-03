package com.raghav.ecommerce.service;

import com.raghav.ecommerce.config.JwtProvider;
import com.raghav.ecommerce.domain.USER_ROLE;
import com.raghav.ecommerce.exception.SellerException;
import com.raghav.ecommerce.model.Cart;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.model.VerificationCode;
import com.raghav.ecommerce.repository.CartRepository;
import com.raghav.ecommerce.repository.SellerRepository;
import com.raghav.ecommerce.repository.UserRepository;
import com.raghav.ecommerce.repository.VerificationCodeRepository;
import com.raghav.ecommerce.request.LoginRequest;
import com.raghav.ecommerce.request.SignupRequest;
import com.raghav.ecommerce.response.AuthResponse;
import com.raghav.ecommerce.service.impl.AuthServiceImpl;
import com.raghav.ecommerce.service.impl.CustomeUserServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Note: Yahan sirf interfaces (repositories, UserService, PasswordEncoder) ko mock kiya hai.
 * JwtProvider, CustomeUserServiceImplementation asli use hote hain aur EmailService ki
 * ek chhoti nakli class (FakeEmailService) banayi hai — taaki naye Java version par bhi test chalein.
 */
class AuthServiceImplTest {

    private static final String EMAIL = "raghav@example.com";

    // ---- Nakli email service: email bhejne ki jagah sirf yaad rakhti hai ----
    static class FakeEmailService extends EmailService {
        String sentTo;
        String sentOtp;
        boolean fail;

        @Override
        public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text) {
            if (fail) throw new RuntimeException("SMTP down");
            this.sentTo = userEmail;
            this.sentOtp = otp;
        }
    }

    private UserService userService;
    private VerificationCodeRepository verificationCodeRepository;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private CartRepository cartRepository;
    private FakeEmailService emailService;
    private JwtProvider jwtProvider;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        verificationCodeRepository = mock(VerificationCodeRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        sellerRepository = mock(SellerRepository.class);
        cartRepository = mock(CartRepository.class);
        emailService = new FakeEmailService();
        jwtProvider = new JwtProvider();

        CustomeUserServiceImplementation customUserDetails =
                new CustomeUserServiceImplementation(userRepository, sellerRepository);

        authService = new AuthServiceImpl(
                userService,
                verificationCodeRepository,
                emailService,
                passwordEncoder,
                userRepository,
                jwtProvider,
                customUserDetails,
                cartRepository
        );
    }

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    private VerificationCode code(String otp) {
        VerificationCode vc = new VerificationCode();
        vc.setEmail(EMAIL);
        vc.setOtp(otp);
        return vc;
    }

    private User existingCustomer() {
        User u = new User();
        u.setId(1L);
        u.setEmail(EMAIL);
        u.setFullName("Raghav Pareek");
        u.setPassword("secret");
        u.setRole(USER_ROLE.ROLE_CUSTOMER);
        return u;
    }

    private LoginRequest login(String otp) {
        LoginRequest req = new LoginRequest();
        req.setEmail(EMAIL);
        req.setOtp(otp);
        return req;
    }

    // ---------------- signin ----------------

    @Test
    @DisplayName("Sahi OTP par login hota hai aur JWT milta hai")
    void signin_correctOtp_returnsJwt() throws Exception {
        when(userRepository.findByEmail(EMAIL)).thenReturn(existingCustomer());
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(code("123456"));

        AuthResponse res = authService.signin(login("123456"));

        assertThat(res.getJwt()).isNotBlank();
        assertThat(jwtProvider.getEmailFromJwtToken("Bearer " + res.getJwt())).isEqualTo(EMAIL);
        assertThat(res.getRole()).isEqualTo(USER_ROLE.ROLE_CUSTOMER);
        assertThat(res.getMessage()).isEqualTo("Login Success");
    }

    @Test
    @DisplayName("Galat OTP par login nahi hota")
    void signin_wrongOtp_throws() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(existingCustomer());
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(code("123456"));

        assertThatThrownBy(() -> authService.signin(login("999999")))
                .isInstanceOf(SellerException.class)
                .hasMessageContaining("wrong otp");
    }

    @Test
    @DisplayName("OTP bheja hi nahi gaya ho to login nahi hota")
    void signin_noOtpSaved_throws() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(existingCustomer());
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(null);

        assertThatThrownBy(() -> authService.signin(login("123456")))
                .isInstanceOf(SellerException.class);
    }

    // ---------------- send OTP ----------------

    @Test
    @DisplayName("Login OTP: 'signing_' hatta hai, purana OTP delete hota hai, naya 6 digit OTP save hota hai")
    void sentLoginOtp_savesNewOtp() throws Exception {
        VerificationCode old = code("111111");
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(old);

        authService.sentLoginOtp("signing_" + EMAIL);

        verify(userService).findUserByEmail(EMAIL);          // user check hua
        verify(verificationCodeRepository).delete(old);       // purana OTP hata

        ArgumentCaptor<VerificationCode> saved = ArgumentCaptor.forClass(VerificationCode.class);
        verify(verificationCodeRepository).save(saved.capture());
        assertThat(saved.getValue().getEmail()).isEqualTo(EMAIL);
        assertThat(saved.getValue().getOtp()).matches("\\d{6}");

        // Wahi OTP email me gaya
        assertThat(emailService.sentTo).isEqualTo(EMAIL);
        assertThat(emailService.sentOtp).isEqualTo(saved.getValue().getOtp());
    }

    @Test
    @DisplayName("Email fail ho jaye tab bhi OTP process nahi rukta")
    void sentLoginOtp_emailFails_doesNotThrow() {
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(null);
        emailService.fail = true;

        assertThatCode(() -> authService.sentLoginOtp(EMAIL)).doesNotThrowAnyException();
        verify(verificationCodeRepository).save(any(VerificationCode.class));
    }

    // ---------------- signup ----------------

    @Test
    @DisplayName("Naye user ka signup: user aur uska cart dono bante hain")
    void createUser_newUser_createsUserAndCart() throws Exception {
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(code("654321"));
        when(userRepository.findByEmail(EMAIL)).thenReturn(null);
        when(passwordEncoder.encode("654321")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        SignupRequest req = new SignupRequest();
        req.setEmail(EMAIL);
        req.setFullName("Raghav Pareek");
        req.setOtp("654321");

        String jwt = authService.createUser(req);

        assertThat(jwtProvider.getEmailFromJwtToken("Bearer " + jwt)).isEqualTo(EMAIL);

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(user.capture());
        assertThat(user.getValue().getFullName()).isEqualTo("Raghav Pareek");
        assertThat(user.getValue().getRole()).isEqualTo(USER_ROLE.ROLE_CUSTOMER);
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    @DisplayName("Signup me galat OTP par user nahi banta")
    void createUser_wrongOtp_throws() {
        when(verificationCodeRepository.findByEmail(EMAIL)).thenReturn(code("654321"));

        SignupRequest req = new SignupRequest();
        req.setEmail(EMAIL);
        req.setFullName("Raghav");
        req.setOtp("000000");

        assertThatThrownBy(() -> authService.createUser(req)).isInstanceOf(SellerException.class);
        verify(userRepository, never()).save(any());
    }
}