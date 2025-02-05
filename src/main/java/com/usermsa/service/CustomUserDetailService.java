package com.usermsa.service;

import com.usermsa.entity.UserEntity;
import com.usermsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> byUserId = userRepository.findByUserId(userId);

        UserEntity user = byUserId.get();

        return User.builder()
                .username(user.getUserId())
                .password(user.getEncryptPw())
                .roles("USER")
                .build();
    }
}
