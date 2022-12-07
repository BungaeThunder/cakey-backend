package bungae.thunder.cakey.message.controller;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.dto.CreateMessageDto;
import bungae.thunder.cakey.message.service.MessageService;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;
    private UserService userService;
    private CakeService cakeService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService,
        CakeService cakeService) {
        this.messageService = messageService;
        this.userService = userService;
        this.cakeService = cakeService;
    }

    @PostMapping
    public ResponseEntity<Long> createMessage(@RequestBody CreateMessageDto createMessageDto) {
        Message message = Message.builder().contents(createMessageDto.getContents())
            .audioUrl(createMessageDto.getAudioUrl()).build();

        User user = userService.getUser(createMessageDto.getSenderId());
        Cake cake = cakeService.getCake(createMessageDto.getCakeId());

        Long messageId = messageService.createMessage(message, user, cake);

        return ResponseEntity.created(URI.create("/messages" + messageId)).body(messageId);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.getMessage(messageId));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessagesByCakeId(@RequestParam Optional<Long> cakeId) {
        /* TODO: request param validation
        if (cakeId.isEmpty()) {
            throw new BadRequestException("cakeId must be provided.");
        }
         */
        return ResponseEntity.ok(messageService.getAllMessagesByCakeId(cakeId.get()));
    }
}
