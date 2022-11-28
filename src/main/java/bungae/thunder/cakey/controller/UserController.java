package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.controller.exception.DataNotFoundException;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired // 자동으로 스프링이 userService를 연결
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId).orElseThrow(() -> new DataNotFoundException()));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Long> signUpUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}