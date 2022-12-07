package bungae.thunder.cakey.message.exception;

import bungae.thunder.cakey.common.error.exception.EntityNotFoundException;

public class MessageNotFoundException extends EntityNotFoundException {
    public MessageNotFoundException(String message) {
        super(message);
    }
}
