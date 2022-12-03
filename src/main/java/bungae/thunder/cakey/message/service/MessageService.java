package bungae.thunder.cakey.message.service;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Long createMessage(Message message, User user, Cake cake) {
        message.setCakeId(cake.getId());
        message.setSenderId(user.getId());

        return messageRepository.save(message).getId();
    }

    public Optional<Message> getMessage(Long id) {
        return messageRepository.findOneById(id);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getAllMessagesByCakeId(Long cakeId) {
        return messageRepository.findAllByCakeId(cakeId);
    }

    public List<Message> getAllMessagesBySenderId(Long senderId) {
        return messageRepository.findAllBySenderId(senderId);
    }
}
