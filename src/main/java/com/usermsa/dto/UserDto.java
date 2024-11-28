package com.usermsa.dto;

import com.usermsa.entity.UserEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    String email;
    String name;
    String pw;
    String userId;
    Date createdAt;
    String encryptPw;

    List<ResponseOrder> orders;

    public UserDto(RequestUser user) {
        this.email = user.email();
        this.name = user.name();
        this.pw = user.pw();
    }

    public UserDto(UserEntity user){
        email = user.getEmail();
        name = user.getName();
        userId = user.getUserId();
        encryptPw = user.getEncryptPw();
    }
}
