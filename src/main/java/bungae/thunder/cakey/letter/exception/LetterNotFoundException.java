package bungae.thunder.cakey.letter.exception;

import bungae.thunder.cakey.common.error.exception.EntityNotFoundException;

public class LetterNotFoundException extends EntityNotFoundException {

    public LetterNotFoundException(String message) {
        super(message);
    }
}
