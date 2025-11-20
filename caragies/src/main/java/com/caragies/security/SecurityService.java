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
        Users users = userRepository.findByUsername(username).get();
        SimpleGrantedAuthority grantedAuthority=new SimpleGrantedAuthority(users.getRole());
        User user = new User(users.getUsername(), users.getPassword(), Collections.singleton(grantedAuthority));
        return user;
    }
}
