package bungae.thunder.cakey.letter.service;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.exception.LetterNotFoundException;
import bungae.thunder.cakey.letter.repository.LetterJpaRepository;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.exception.UserNotFoundException;
import bungae.thunder.cakey.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterService {
    LetterJpaRepository letterJpaRepository;
    UserService userService;
    CakeService cakeService;

    @Autowired
    public LetterService(
            LetterJpaRepository letterJpaRepository,
            UserService userService,
            CakeService cakeService) {
        this.letterJpaRepository = letterJpaRepository;
        this.userService = userService;
        this.cakeService = cakeService;
    }

    public Letter createLetter(String contents, String audioUrl, Long senderId, Long cakeId) {
        User user =
                userService
                        .getUser(senderId)
                        .orElseThrow(() -> new UserNotFoundException("유저가 존재하지 않습니다"));
        ;
        Cake cake = cakeService.getCake(cakeId);
        Letter letter =
                Letter.builder()
                        .contents(contents)
                        .audioUrl(audioUrl)
                        .bookmark(false)
                        .isRead(false)
                        .build();
        letter.setCake(cake);
        letter.setSender(user);

        return letterJpaRepository.save(letter);
    }

    public Letter getLetter(Long id) {
        return letterJpaRepository
                .findById(id)
                .orElseThrow(() -> new LetterNotFoundException("편지가 존재하지 않습니다"));
    }

    public List<Letter> getAllLetters() {
        return letterJpaRepository.findAll();
    }

    public List<Letter> getAllLettersByCakeId(Long cakeId) {
        return letterJpaRepository.findAllByCakeId(cakeId);
    }

    public List<Letter> getAllLettersBySenderId(Long senderId) {
        return letterJpaRepository.findAllBySenderId(senderId);
    }
}
