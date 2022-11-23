package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.controller.exception.DataNotFoundException;
import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.service.CakeService;
import bungae.thunder.cakey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<HttpStatus> createCake(@RequestBody User user) {

        checkValidateUserId(user.getId());
        cakeService.makeCake(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{cakeId}")
    public ResponseEntity<Cake> getCake(@PathVariable Long cakeId) {
        return ResponseEntity.ok(cakeService.getCake(cakeId).orElseThrow(() -> new DataNotFoundException()));
    }

    @GetMapping
    public ResponseEntity<List<Cake>> getAllCakes(@RequestParam Long userId) {
        checkValidateUserId(userId);
        return ResponseEntity.ok(cakeService.getAllCake(userId));
    }

    private void checkValidateUserId(Long userId) {
        Optional<User> user = userService.findOne(userId);
        if (user.isEmpty()) {
            throw new DataNotFoundException();
        }
    }
}
