package com.usermsa.controller;

import com.usermsa.dto.RequestUser;
import com.usermsa.dto.ResponseUser;
import com.usermsa.dto.UserDto;
import com.usermsa.service.UserService;
import com.usermsa.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
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
    public ResponseEntity createUser(@RequestBody RequestUser user) {
        userService.createUser(new UserDto(user));
        return new ResponseEntity(HttpStatus.CREATED);
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
