package bungae.thunder.cakey.letter.exception;

import bungae.thunder.cakey.common.error.exception.EntityNotFoundException;

public class InvalidLetterException extends EntityNotFoundException {

    public InvalidLetterException(String message) {
        super(message);
    }
}
