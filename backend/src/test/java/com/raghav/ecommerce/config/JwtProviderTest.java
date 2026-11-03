package com.raghav.ecommerce.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtProviderTest {

    private static final String EMAIL = "raghav@example.com";

    private JwtProvider jwtProvider;
    private Authentication auth;

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider();
        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        auth = new UsernamePasswordAuthenticationToken(EMAIL, null, roles);
    }

    @Test
    @DisplayName("Token banta hai aur usme 3 hisse (header.payload.signature) hote hain")
    void generateToken_returnsValidJwt() {
        String token = jwtProvider.generateToken(auth);

        assertThat(token).isNotBlank();
        assertThat(token.split("\\.")).hasSize(3);
    }

    @Test
    @DisplayName("'Bearer <token>' se sahi email nikalta hai")
    void getEmail_withBearerPrefix() {
        String token = jwtProvider.generateToken(auth);

        assertThat(jwtProvider.getEmailFromJwtToken("Bearer " + token)).isEqualTo(EMAIL);
    }

    @Test
    @DisplayName("Faltu space hone par bhi email sahi nikalta hai (login bug ka test)")
    void getEmail_withExtraSpaces() {
        String token = jwtProvider.generateToken(auth);

        assertThat(jwtProvider.getEmailFromJwtToken("  Bearer    " + token + "  ")).isEqualTo(EMAIL);
    }

    @Test
    @DisplayName("Galat token par exception aata hai")
    void getEmail_invalidToken_throws() {
        assertThatThrownBy(() -> jwtProvider.getEmailFromJwtToken("Bearer abc.def.ghi"))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Roles comma se jud kar ek string bante hain")
    void populateAuthorities_joinsRoles() {
        String roles = jwtProvider.populateAuthorities(List.of(
                new SimpleGrantedAuthority("ROLE_CUSTOMER"),
                new SimpleGrantedAuthority("ROLE_SELLER")));

        assertThat(roles.split(",")).containsExactlyInAnyOrder("ROLE_CUSTOMER", "ROLE_SELLER");
    }
}