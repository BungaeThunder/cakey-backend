package bungae.thunder.cakey.cake.controller;

import bungae.thunder.cakey.cake.converter.CakeResponseDtoConverter;
import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.dto.CakeByGuestResponse;
import bungae.thunder.cakey.cake.dto.CakeByOwnerResponse;
import bungae.thunder.cakey.cake.dto.CakeResponseDto;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.service.LetterService;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
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

    private final UserService userService;

    private final LetterService letterService;

    private CakeResponseDtoConverter cakeResponseDtoConverter;

    @Autowired
    public CakeController(
            CakeService cakeService,
            UserService userService,
            LetterService letterService,
            CakeResponseDtoConverter cakeResponseDtoConverter) {
        this.cakeService = cakeService;
        this.userService = userService;
        this.letterService = letterService;
        this.cakeResponseDtoConverter = cakeResponseDtoConverter;
    }

    @GetMapping("/create/{userId}")
    public ResponseEntity<CakeResponseDto> createCake(@PathVariable Long userId) {
        User dbUser = userService.getUser(userId);
        return ResponseEntity.ok(cakeResponseDtoConverter.convert(cakeService.createCake(dbUser)));
    }

    @GetMapping("/{cakeId}")
    public ResponseEntity<CakeResponseDto> getCake(@PathVariable Long cakeId) {
        return ResponseEntity.ok(cakeResponseDtoConverter.convert(cakeService.getCake(cakeId)));
    }

    /*
     케이크를 방문한 Guest가 cakeId에 해당하는 케이크 정보를 조회.
     해당 케이크에 Guest가 작성한 편지 여부와 그 내용을 응답에 함께 포함한다.
     + 편지를 얼마나 많이 받았는지 대략적인 정보 추가?
     */
    @GetMapping("/cake/guest/{cakeId}")
    public ResponseEntity<CakeByGuestResponse> getCakeByGuest(@PathVariable Long cakeId) {
        //TODO: 이후 auth service 에서 사용자 정보를 가져오는것으로 변경
        Long userId = 1L;

        Cake cake = cakeService.getCake(cakeId);
        User owner = userService.getUser(cake.getUser().getId());
        Letter writeLetter = letterService.getLetterBySenderIdAndCakeId(userId, cakeId);

        CakeByGuestResponse response = new CakeByGuestResponse(cake, owner, writeLetter);
        return ResponseEntity.ok(response);
    }

    /*
     케이크를 방문한 Owner가 cakeId에 해당하는 케이크 정보를 조회.
     케이크가 받은 편지 id의 리스트를 함께 반환
     */
    @GetMapping("/cake/owner/{cakeId}")
    public ResponseEntity<CakeByOwnerResponse> getCakeByOwner(@PathVariable Long cakeId) {
        //TODO: 이후 auth service 에서 사용자 정보를 가져오는것으로 변경
        Long userId = 1L;

        //TODO: cake 의 owner 와 현재 로그인한 user가 일치하는지 확인?
        Cake cake = cakeService.getCake(cakeId);
        User owner = userService.getUser(userId);
        List<Letter> receivedLetters = letterService.getAllLettersByCakeId(cakeId);

        CakeByOwnerResponse response = new CakeByOwnerResponse(cake, owner, receivedLetters);
        return ResponseEntity.ok(response);
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
