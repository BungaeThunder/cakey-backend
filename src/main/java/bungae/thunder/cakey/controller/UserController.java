package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
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
}