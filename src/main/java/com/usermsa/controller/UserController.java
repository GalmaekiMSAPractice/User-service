package com.usermsa.controller;

import com.usermsa.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private Greeting greeting;
    @GetMapping("/healthcheck")
    public String status() {
        return "Working Now";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }
}
