package bungae.thunder.cakey.user.controller;

import bungae.thunder.cakey.user.converter.UserResponseDtoConverter;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.dto.UserResponseDto;
import bungae.thunder.cakey.user.dto.UserSignUpRequestDto;
import bungae.thunder.cakey.user.service.UserService;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

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

    @GetMapping("/signUp/naver")
    public String naverConnect() throws UnsupportedEncodingException {
        String clientId = System.getenv("NAVER_LOGIN_CLIENT_ID");
        String redirectURI = URLEncoder.encode("https://" + System.getenv(
                "SERVER_DOMAIN") + "/signUp/naver/callback", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;

        return "redirect:" + apiURL;
    }

    @RequestMapping(value = "/signUp/naver/callback", method =
            { RequestMethod.GET, RequestMethod.POST }, produces =
            "application/json")
    public void naverLogin(@RequestParam(value = "code") String code,
                           @RequestParam(value = "state") String state) {
        WebClient webClient = WebClient.builder().baseUrl("https://nid.naver" +
                ".com").defaultHeader(HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_JSON_VALUE).build();

        JSONObject response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2.0/token")
                        .queryParam("client_id", System.getenv("NAVER_LOGIN_CLIENT_ID"))
                        .queryParam("client_secret", System.getenv(
                                "NAVER_LOGIN_SECRET"))
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("state", state)
                        .queryParam("code", code)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();

        String token = (String) response.get("access_token");

        // TODO: 토큰으로 사용자 정보 가져와서 저장
    }
}
