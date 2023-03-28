package bungae.thunder.cakey.user.controller;

import bungae.thunder.cakey.user.converter.UserResponseDtoConverter;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.dto.UserResponseDto;
import bungae.thunder.cakey.user.dto.UserSignUpDto;
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

    private UserResponseDtoConverter UserResponseDtoConverter;

    @Autowired
    public UserController(
            UserService userService, UserResponseDtoConverter UserResponseDtoConverter) {
        this.userService = userService;
        this.UserResponseDtoConverter = UserResponseDtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        List<UserResponseDto> responses =
                allUsers.stream()
                        .map(user -> UserResponseDtoConverter.convert(user))
                        .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(UserResponseDtoConverter.convert(userService.getUser(userId)));
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> signUpUser(@RequestBody UserSignUpDto userSignUpDto) {
        User user = User.builder().email(userSignUpDto.getEmail())
                .name(userSignUpDto.getName())
                .birthday(userSignUpDto.getBirthday())
                .build();
        return ResponseEntity.ok(UserResponseDtoConverter.convert(userService.createUser(user)));
    }
}
