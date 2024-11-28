package com.usermsa.dto;

import com.usermsa.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

    public ResponseUser(UserDto user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.userId = user.getUserId();
        this.orders = user.getOrders();
    }

    public ResponseUser(UserEntity user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.userId = user.getUserId();
    }
}
