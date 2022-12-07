package bungae.thunder.cakey.cake.controller;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.cake.service.CakeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cakes")
@Slf4j
public class CakeController {

    private final CakeService cakeService;

    @Autowired
    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @PostMapping
    public ResponseEntity<Long> createCake(@RequestBody User user) {
        return ResponseEntity.ok(cakeService.createCake(user));
    }

    @GetMapping("/{cakeId}")
    public ResponseEntity<Cake> getCake(@PathVariable Long cakeId) {
        return ResponseEntity.ok(cakeService.getCake(cakeId));
    }

    @GetMapping
    public ResponseEntity<List<Cake>> getAllCakes(@RequestParam Long userId) {
        return ResponseEntity.ok(cakeService.getAllCakes(userId));
    }
}
