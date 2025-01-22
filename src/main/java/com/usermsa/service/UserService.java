package com.usermsa.service;

import com.usermsa.dto.LoginDto;
import com.usermsa.dto.RequestLogin;
import com.usermsa.dto.UserDto;
import com.usermsa.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    List<UserEntity> getUserAll();

    LoginDto login(RequestLogin login);
}
