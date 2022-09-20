package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    private final UserService userService;

    @Autowired // 자동으로 스프링이 userService를 연결
    public UserController(UserService userService) {
        this.userService = userService;
    }
}