package com.example.demo.security;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.demo.resposity.UserRepository;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceimpl implements UserDetailsService {
    UserRepository userRepository;
    public UserDetailsServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        if(user.getStatus() == UserStatus.LOCKED || user.getStatus() == UserStatus.PENDING) {
            throw new UsernameNotFoundException("User with email: " + username + " is " + user.getStatus().name().toLowerCase());
        }
        return new UserDetailsimpl(
            user.getId(),
            user.getPassword(),
            user.getEmail(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
    
}
