package bungae.thunder.cakey.user.controller;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.service.LetterService;
import bungae.thunder.cakey.user.converter.UserResponseDtoConverter;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.dto.UserDetailResponseDTO;
import bungae.thunder.cakey.user.dto.UserResponseDto;
import bungae.thunder.cakey.user.dto.UserSignUpRequestDto;
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
    private UserService userService;
    private CakeService cakeService;
    private LetterService letterService;

    private UserResponseDtoConverter UserResponseDtoConverter;

    @Autowired
    public UserController(
            UserService userService,
            CakeService cakeService,
            LetterService letterService,
            UserResponseDtoConverter UserResponseDtoConverter) {
        this.userService = userService;
        this.cakeService = cakeService;
        this.letterService = letterService;
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
    public ResponseEntity<UserDetailResponseDTO> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        List<Cake> cakes = cakeService.getAllCakes(userId);
        List<Letter> letters = letterService.getAllLettersBySenderId(userId);
        UserDetailResponseDTO response = new UserDetailResponseDTO(user, cakes, letters);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> signUpUser(
            @RequestBody UserSignUpRequestDto UserSignUpRequestDto) {
        User user =
                User.builder()
                        .email(UserSignUpRequestDto.getEmail())
                        .name(UserSignUpRequestDto.getName())
                        .birthday(UserSignUpRequestDto.getBirthday())
                        .build();
        return ResponseEntity.ok(UserResponseDtoConverter.convert(userService.createUser(user)));
    }
}
