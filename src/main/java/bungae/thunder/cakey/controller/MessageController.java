package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.dto.CreateMessageDto;
import bungae.thunder.cakey.exception.BadRequestException;
import bungae.thunder.cakey.exception.NotFoundException;
import bungae.thunder.cakey.service.CakeService;
import bungae.thunder.cakey.service.MessageService;
import bungae.thunder.cakey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/messages")
public class MessageController {
    private MessageService messageService;
    private UserService userService;
    private CakeService cakeService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService, CakeService cakeService) {
        this.messageService = messageService;
        this.userService = userService;
        this.cakeService = cakeService;
    }

    @PostMapping()
    public ResponseEntity<Long> createMessage(@RequestBody CreateMessageDto createMessageDto) {
        Message message = Message.builder().contents(createMessageDto.getContents()).audioUrl(createMessageDto.getAudioUrl()).build();

        Optional<User> user = userService.findOne(createMessageDto.getSenderId());
        Optional<Cake> cake = cakeService.getCake(createMessageDto.getCakeId());

        if (user.isEmpty()) {
            String reason = String.format("No such user %d\n", createMessageDto.getSenderId());
            System.out.println(reason);
            throw new NotFoundException(reason);
        }

        if (cake.isEmpty()) {
            String reason = String.format("No such cake %d\n", createMessageDto.getCakeId());
            System.out.println(reason);
            throw new NotFoundException(reason);
        }

        Long messageId = messageService.createMessage(message, user.get(), cake.get());

        return ResponseEntity.created(URI.create("/messages" + messageId)).body(messageId);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Long messageId) {
        Optional<Message> message = messageService.findMessage(messageId);
        if (message.isEmpty()) {
            throw new NotFoundException();
        }

        return ResponseEntity.ok(message.get());
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getMessagesByCakeId(@RequestParam Optional<Long> cakeId) {
        if (cakeId.isEmpty()) {
            throw new BadRequestException("cakeId must be provided.");
        }

        return ResponseEntity.ok(messageService.findAllMessagesByCakeId(cakeId.get()));
    }
}
