package com.usermsa.service;

import com.usermsa.dto.UserDto;
import com.usermsa.entity.UserEntity;
import com.usermsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptPw(passwordEncoder.encode(userDto.getPw()));
        userRepository.save(new UserEntity(userDto));
        return userDto;
    }
}
