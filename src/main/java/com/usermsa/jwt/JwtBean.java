package com.usermsa.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtBean {
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expiration_time}")
    private Long expiration_time;
}
