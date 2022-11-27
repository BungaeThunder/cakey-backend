package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.exception.NotFoundException;
import bungae.thunder.cakey.service.CakeService;
import bungae.thunder.cakey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cakes")
@Slf4j
public class CakeController {
    private final CakeService cakeService;
    private final UserService userService;

    @Autowired
    public CakeController(CakeService cakeService, UserService userService) {
        this.cakeService = cakeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> createCake(@RequestBody User user) {
        validateUserId(user.getId());
        return ResponseEntity.ok(cakeService.createCake(user));
    }

    @GetMapping("/{cakeId}")
    public ResponseEntity<Cake> getCake(@PathVariable Long cakeId) {
        return ResponseEntity.ok(cakeService.getCake(cakeId).orElseThrow(() -> new NotFoundException()));
    }

    @GetMapping
    public ResponseEntity<List<Cake>> getAllCakes(@RequestParam Long userId) {
        validateUserId(userId);
        return ResponseEntity.ok(cakeService.getAllCakes(userId));
    }

    // TODO: 공통 validate 로직으로 빼기
    private void validateUserId(Long userId) {
        Optional<User> user = userService.getUser(userId);
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
    }
}
