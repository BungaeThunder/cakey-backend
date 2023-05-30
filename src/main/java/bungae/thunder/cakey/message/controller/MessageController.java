package bungae.thunder.cakey.message.controller;

import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.converter.MessageResponseDtoConverter;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.dto.CreateMessageRequestDto;
import bungae.thunder.cakey.message.dto.MessageResponseDto;
import bungae.thunder.cakey.message.service.MessageService;
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
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;
    private UserService userService;
    private CakeService cakeService;
    private MessageResponseDtoConverter messageResponseDtoConverter;

    @Autowired
    public MessageController(
            MessageService messageService,
            UserService userService,
            CakeService cakeService,
            MessageResponseDtoConverter messageResponseDtoConverter) {
        this.messageService = messageService;
        this.userService = userService;
        this.cakeService = cakeService;
        this.messageResponseDtoConverter = messageResponseDtoConverter;
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(
            @RequestBody CreateMessageRequestDto createMessageRequestDto) {
        Message createdMessage =
                messageService.createMessage(
                        createMessageRequestDto.getContents(),
                        createMessageRequestDto.getAudioUrl(),
                        createMessageRequestDto.getSenderId(),
                        createMessageRequestDto.getCakeId());

        return ResponseEntity.created(URI.create("/messages" + createdMessage.getId()))
                .body(messageResponseDtoConverter.convert(createdMessage));
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> getMessage(@PathVariable Long messageId) {
        return ResponseEntity.ok(
                messageResponseDtoConverter.convert(messageService.getMessage(messageId)));
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessagesByCakeId(@RequestParam Long cakeId) {
        /* TODO: request param validation
        if (cakeId.isEmpty()) {
            throw new BadRequestException("cakeId must be provided.");
        }
         */
        return ResponseEntity.ok(
                messageService.getAllMessagesByCakeId(cakeId).stream()
                        .map(message -> messageResponseDtoConverter.convert(message))
                        .collect(Collectors.toList()));
    }
}
