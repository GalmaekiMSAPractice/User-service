package com.usermsa.service;

import com.usermsa.dto.LoginDto;
import com.usermsa.dto.RequestLogin;
import com.usermsa.dto.UserDto;
import com.usermsa.entity.UserEntity;
import com.usermsa.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptPw(passwordEncoder.encode(userDto.getPw()));
        userRepository.save(new UserEntity(userDto));
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto user = new UserDto(userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("UserNotFound")));

        user.setOrders(new ArrayList<>());
        return user;
    }

    @Override
    public List<UserEntity> getUserAll() {
        return userRepository.findAll();
    }

    @Override
    public LoginDto login(RequestLogin login) {
        UserEntity user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(login.getPassword(), user.getEncryptPw()))
            throw new RuntimeException();

        String token = Jwts.builder()
                .setSubject(user.getUserId())
                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS256, env.getProperty("token.secret"))
                .compact();

        return new LoginDto(token, user.getUserId());
    }
}
