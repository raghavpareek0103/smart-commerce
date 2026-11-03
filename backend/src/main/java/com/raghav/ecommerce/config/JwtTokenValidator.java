package com.raghav.ecommerce.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(JwtConstant.JWT_HEADER);

        if (header != null) {
            // "Bearer " hata kar aage-peeche ke saare space bhi hata do
            String jwt = header.trim();
            if (jwt.regionMatches(true, 0, "Bearer", 0, 6)) {
                jwt = jwt.substring(6).trim();
            }

            // Login nahi hai to frontend "null" / "undefined" bhej deta hai — unhe chhod do
            if (!jwt.isEmpty() && !"null".equals(jwt) && !"undefined".equals(jwt)) {
                try {
                    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();

                    String email = String.valueOf(claims.get("email"));
                    String authorities = String.valueOf(claims.get("authorities"));

                    List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } catch (Exception e) {
                    // Galat / expire token — request ko bina login ke aage jaane do
                    System.out.println("invalid or expired token: " + e.getMessage());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}