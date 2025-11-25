package com.caragies.security;

import com.caragies.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            String username = jwtUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                String roleFromToken = jwtUtil.extractRole(token); // e.g. "USER" or "ROLE_USER"

                if (roleFromToken != null) {
                    // Normalize: remove ROLE_ if present, then uppercase
                    String normalizedRole = roleFromToken.replace("ROLE_", "").toUpperCase(); // "USER"

                    String password = userRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not found: " + username))
                            .getPassword();

                    // 👇 Authority will be exactly "USER"/"ADMIN"/"VENDOR"
                    List<GrantedAuthority> authorities =
                            List.of(new SimpleGrantedAuthority(normalizedRole));

                    UserDetails userDetails = new User(
                            username,
                            password,
                            authorities
                    );

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
