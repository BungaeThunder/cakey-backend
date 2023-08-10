package bungae.thunder.cakey.letter.controller;

import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.letter.converter.LetterResponseDtoConverter;
import bungae.thunder.cakey.letter.converter.OwnerLetterResponseDtoConverter;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.dto.CreateLetterRequestDto;
import bungae.thunder.cakey.letter.dto.LetterResponseDto;
import bungae.thunder.cakey.letter.dto.ModifyLetterRequestDto;
import bungae.thunder.cakey.letter.dto.OwnerLetterResponseDto;
import bungae.thunder.cakey.letter.service.LetterService;
import bungae.thunder.cakey.user.service.UserService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/letters")
public class LetterController {

    private LetterService letterService;
    private UserService userService;
    private CakeService cakeService;
    private LetterResponseDtoConverter letterResponseDtoConverter;
    private OwnerLetterResponseDtoConverter ownerLetterResponseDtoConverter;

    @Autowired
    public LetterController(
            LetterService letterService,
            UserService userService,
            CakeService cakeService,
            LetterResponseDtoConverter letterResponseDtoConverter) {
        this.letterService = letterService;
        this.userService = userService;
        this.cakeService = cakeService;
        this.letterResponseDtoConverter = letterResponseDtoConverter;
    }

    @PostMapping
    public ResponseEntity<LetterResponseDto> createLetter(
            @RequestBody CreateLetterRequestDto createLetterRequestDto) {
        Letter createdLetter =
                letterService.createLetter(
                        createLetterRequestDto.getContents(),
                        createLetterRequestDto.getAudioUrl(),
                        createLetterRequestDto.getSenderId(),
                        createLetterRequestDto.getCakeId());

        return ResponseEntity.created(URI.create("/letters" + createdLetter.getId()))
                .body(letterResponseDtoConverter.convert(createdLetter));
    }

    @GetMapping("/{letterId}")
    public ResponseEntity<LetterResponseDto> getLetter(@PathVariable Long letterId) {
        // TODO: Owner or sender 인증/인가
        return ResponseEntity.ok(
                letterResponseDtoConverter.convert(letterService.getLetter(letterId)));
    }

    @GetMapping("/by-cake")
    public ResponseEntity<List<OwnerLetterResponseDto>> getLettersByCakeId(
            @RequestParam Long cakeId) {
        /* TODO: request param validation
        if (cakeId.isEmpty()) {
            throw new BadRequestException("cakeId must be provided.");
        }
         */
        return ResponseEntity.ok(
                letterService.getAllLettersByCakeId(cakeId).stream()
                        .map(letter -> ownerLetterResponseDtoConverter.convert(letter))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/by-sender")
    public ResponseEntity<List<LetterResponseDto>> getLettersBySenderId(
        @RequestParam Long senderId) {
        return ResponseEntity.ok(
            letterService.getAllLettersBySenderId(senderId).stream()
                .map(letter -> letterResponseDtoConverter.convert(letter))
                .collect(Collectors.toList()));
    }

    @PatchMapping("/{letterId}")
    public ResponseEntity<LetterResponseDto> modifyLetter(
        @RequestBody ModifyLetterRequestDto modifyLetterRequestDto) {
        Letter modifiedLetter =
            letterService.modifyLetter(
                modifyLetterRequestDto.getId(),
                modifyLetterRequestDto.getContents(),
                modifyLetterRequestDto.getAudioUrl(),
                modifyLetterRequestDto.getCakeId(),
                modifyLetterRequestDto.getSenderId());

        return ResponseEntity.ok(letterResponseDtoConverter.convert(modifiedLetter));
    }
}
