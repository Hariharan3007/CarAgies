package com.caragies.security;

import com.caragies.entitymodel.Users;
import com.caragies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class SecurityService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Store roles in DB as "USER", "ADMIN", "VENDOR"
        String role = users.getRole().toUpperCase();  // "ADMIN"

        SimpleGrantedAuthority grantedAuthority =
                new SimpleGrantedAuthority(role);      // authority = "ADMIN"

        return new org.springframework.security.core.userdetails.User(
                users.getUsername(),
                users.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

}
