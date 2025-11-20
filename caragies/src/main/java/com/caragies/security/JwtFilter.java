package com.caragies.security;

import com.caragies.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token;
        String role;
        String username;
        if(header!=null && header.startsWith("Bearer ")){
            token=header.substring(7);
            username = jwtUtil.extractUsername(token);
            role = jwtUtil.extractRole(token);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                String password = userRepository.findByUsername(username).get().getPassword();
                UserDetails user = User
                                    .builder()
                                    .username(username)
                                    .password(password)
                                    .roles(role.replace("ROLE_", ""))
                                    .build();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
