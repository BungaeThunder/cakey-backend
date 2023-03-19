package bungae.thunder.cakey.cake.controller;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.converter.CakeResponseDtoConverter;
import bungae.thunder.cakey.cake.dto.CakeResponseDto;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cakes")
@Slf4j
public class CakeController {

    private final CakeService cakeService;

    private CakeResponseDtoConverter cakeResponseDtoConverter;

    @Autowired
    public CakeController(CakeService cakeService,
        CakeResponseDtoConverter cakeResponseDtoConverter) {
        this.cakeService = cakeService;
        this.cakeResponseDtoConverter = cakeResponseDtoConverter;
    }

    @PostMapping
    public ResponseEntity<CakeResponseDto> createCake(@RequestBody User user) {
        return ResponseEntity.ok(cakeResponseDtoConverter.convert(cakeService.createCake(user)));
    }

    @GetMapping("/{cakeId}")
    public ResponseEntity<CakeResponseDto> getCake(@PathVariable Long cakeId) {
        return ResponseEntity.ok(cakeResponseDtoConverter.convert(cakeService.getCake(cakeId)));
    }

    @GetMapping
    public ResponseEntity<List<CakeResponseDto>> getAllCakes(@RequestParam Long userId) {
        List<Cake> allCakes = cakeService.getAllCakes(userId);
        List<CakeResponseDto> responses =
            allCakes.stream()
                .map(cake -> cakeResponseDtoConverter.convert(cake))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
