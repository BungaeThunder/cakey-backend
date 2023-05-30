package bungae.thunder.cakey.message.service;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.exception.MessageNotFoundException;
import bungae.thunder.cakey.message.repository.MessageJpaRepository;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    MessageJpaRepository messageJpaRepository;
    UserService userService;
    CakeService cakeService;

    @Autowired
    public MessageService(
            MessageJpaRepository messageJpaRepository,
            UserService userService,
            CakeService cakeService) {
        this.messageJpaRepository = messageJpaRepository;
        this.userService = userService;
        this.cakeService = cakeService;
    }

    public Message createMessage(String contents, String audioUrl, Long senderId, Long cakeId) {
        User user = userService.getUser(senderId);
        Cake cake = cakeService.getCake(cakeId);
        Message message = Message.builder().contents(contents).audioUrl(audioUrl).build();
        message.setCake(cake);
        message.setSender(user);

        return messageJpaRepository.save(message);
    }

    public Message getMessage(Long id) {
        return messageJpaRepository
                .findById(id)
                .orElseThrow(() -> new MessageNotFoundException("메시지가 존재하지 않습니다"));
    }

    public List<Message> getAllMessages() {
        return messageJpaRepository.findAll();
    }

    public List<Message> getAllMessagesByCakeId(Long cakeId) {
        return messageJpaRepository.findAllByCakeId(cakeId);
    }

    public List<Message> getAllMessagesBySenderId(Long senderId) {
        return messageJpaRepository.findAllBySenderId(senderId);
    }
}
