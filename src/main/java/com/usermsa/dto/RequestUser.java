package com.usermsa.dto;

public record RequestUser(
        String email,
        String name,
        String pw
) {
}
