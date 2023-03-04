package bungae.thunder.cakey.message.service;

import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.exception.MessageNotFoundException;
import bungae.thunder.cakey.message.repository.MessageJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    MessageJpaRepository messageJpaRepository;

    @Autowired
    public MessageService(MessageJpaRepository messageJpaRepository) {
        this.messageJpaRepository = messageJpaRepository;
    }

    //    public Long createMessage(Message message, User user, Cake cake) {
    //        message.setCake(cake);
    //        message.setSender(user);
    //
    //        return messageRepository.save(message).getId();
    //    }

    public Message getMessage(Long id) {
        return messageJpaRepository
                .findById(id)
                .orElseThrow(() -> new MessageNotFoundException("메시지가 존재하지 않습니다"));
    }

    public List<Message> getAllMessages() {
        return messageJpaRepository.findAll();
    }

    //    public List<Message> getAllMessagesByCakeId(Long cakeId) {
    //        return messageRepository.findAllByCakeId(cakeId);
    //    }
    //
    //    public List<Message> getAllMessagesBySenderId(Long senderId) {
    //        return messageRepository.findAllBySenderId(senderId);
    //    }
}
