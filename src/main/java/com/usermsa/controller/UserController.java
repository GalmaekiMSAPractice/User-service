package com.usermsa.controller;

import com.usermsa.dto.*;
import com.usermsa.service.UserService;
import com.usermsa.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private Greeting greeting;

    @GetMapping("/healthcheck")
    public String status() {
        return "Working Now";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody RequestUser user) {
        UserDto createdUser = userService.createUser(new UserDto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody RequestLogin login) {
        HttpHeaders headers = new HttpHeaders();

        LoginDto loginDto = userService.login(login);

        headers.add("token", loginDto.token());
        headers.add("userId", loginDto.userId());

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        return ResponseEntity.ok(
                userService.getUserAll().stream().map(ResponseUser::new)
                        .toList()
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(new ResponseUser(userService.getUserByUserId(userId)));
    }
}
