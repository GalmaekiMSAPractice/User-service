package com.usermsa.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    String email;
    String name;
    String pw;
    String userId;
    Date createdAt;
    String encryptPw;

    public UserDto(RequestUser user) {
        this.email = user.email();
        this.name = user.name();
        this.pw = user.pw();
        this.userId = userId;
        this.createdAt = createdAt;
        this.encryptPw = encryptPw;
    }
}
