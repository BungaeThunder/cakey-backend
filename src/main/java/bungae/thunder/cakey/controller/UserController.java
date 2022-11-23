package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public List<User> getUsers() {
        return userService.findMembers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return userService.findOne(userId);
    }

    // ref : https://github.com/in28minutes/spring-boot-examples/blob/8964cf1346c21b1f8d05f126222e3b5e08d8d558/spring-boot-rest-services/src/main/java/com/in28minutes/springboot/controller/StudentController.java#L31-L46
    @PostMapping("signUp")
    public ResponseEntity<Void> signUpUser(@RequestBody User newUser) {

        Long userId = userService.signUp(newUser);
        if (userId == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}