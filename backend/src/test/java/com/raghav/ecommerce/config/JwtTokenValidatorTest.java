package com.raghav.ecommerce.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenValidatorTest {

    private final JwtTokenValidator filter = new JwtTokenValidator();
    private final JwtProvider jwtProvider = new JwtProvider();

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    private String validToken() {
        return jwtProvider.generateToken(new UsernamePasswordAuthenticationToken(
                "raghav@example.com", null, List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))));
    }

    private MockFilterChain run(String authHeader) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/users/profile");
        if (authHeader != null) request.addHeader("Authorization", authHeader);
        MockFilterChain chain = new MockFilterChain();
        filter.doFilter(request, new MockHttpServletResponse(), chain);
        return chain;
    }

    @Test
    @DisplayName("Sahi token par user login maana jaata hai")
    void validToken_setsAuthentication() throws Exception {
        MockFilterChain chain = run("Bearer " + validToken());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertThat(auth).isNotNull();
        assertThat(auth.getName()).isEqualTo("raghav@example.com");
        assertThat(auth.getAuthorities()).extracting("authority").containsExactly("ROLE_CUSTOMER");
        assertThat(chain.getRequest()).isNotNull(); // request aage gayi
    }

    @Test
    @DisplayName("Token me extra space ho tab bhi login maana jaata hai (purana bug)")
    void tokenWithExtraSpaces_stillWorks() throws Exception {
        run("Bearer    " + validToken() + "   ");

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
    }

    @Test
    @DisplayName("Galat token par error nahi aata, bas user login nahi maana jaata")
    void invalidToken_doesNotCrash() throws Exception {
        MockFilterChain chain = run("Bearer this.is.wrong");

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        assertThat(chain.getRequest()).isNotNull();
    }

    @Test
    @DisplayName("'Bearer null' (login nahi hai) chupchaap ignore hota hai")
    void nullToken_isIgnored() throws Exception {
        MockFilterChain chain = run("Bearer null");

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        assertThat(chain.getRequest()).isNotNull();
    }

    @Test
    @DisplayName("Header hi na ho to bhi request aage jaati hai")
    void noHeader_passesThrough() throws Exception {
        MockFilterChain chain = run(null);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        assertThat(chain.getRequest()).isNotNull();
    }
}