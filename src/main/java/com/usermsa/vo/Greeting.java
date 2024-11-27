package com.usermsa.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Greeting {

    @Value("${env.message}")
    private String message;
}
