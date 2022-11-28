package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MessageRepository;
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

    public Optional<Message> findMessage(Long id) {
        return messageRepository.findOneById(id);
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> findAllMessagesByCakeId(Long cakeId) {
        return messageRepository.findAllByCakeId(cakeId);
    }

    public List<Message> findAllMessagesBySenderId(Long senderId) {
        return messageRepository.findAllBySenderId(senderId);
    }
}
