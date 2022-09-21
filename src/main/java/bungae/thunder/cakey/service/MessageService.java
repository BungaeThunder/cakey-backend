package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

enum FindMessageOption { BY_CAKE_ID, BY_SENDER_ID }

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public Long createMessage(Message message, User user) {
        // TODO: message.setCakeId(cakeId)
        message.setSenderId(user.getId());

        return messageRepository.save(message).getId();
    }

    public Optional<Message> findMessage(Long id) {
        return messageRepository.findOneById(id);
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> findAllMessages(Long id, FindMessageOption option) {
        if (option == FindMessageOption.BY_CAKE_ID) {
            // TODO: find all by cake id
        } else if (option == FindMessageOption.BY_SENDER_ID) {
            return messageRepository.findAllBySenderId(id);
        }

        throw new RuntimeException("No such find option");
    }
}
