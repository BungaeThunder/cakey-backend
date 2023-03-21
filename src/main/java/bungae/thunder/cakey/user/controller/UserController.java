package bungae.thunder.cakey.user.controller;

import bungae.thunder.cakey.user.converter.UserResponseDtoConverter;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.dto.UserResponseDTO;
import bungae.thunder.cakey.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private UserResponseDtoConverter userResponseDtoConverter;

    @Autowired
    public UserController(
            UserService userService, UserResponseDtoConverter userResponseDtoConverter) {
        this.userService = userService;
        this.userResponseDtoConverter = userResponseDtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        List<UserResponseDTO> responses =
                allUsers.stream()
                        .map(user -> userResponseDtoConverter.convert(user))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userResponseDtoConverter.convert(userService.getUser(userId)));
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDTO> signUpUser(@RequestBody User user) {
        return ResponseEntity.ok(userResponseDtoConverter.convert(userService.createUser(user)));
    }
}
