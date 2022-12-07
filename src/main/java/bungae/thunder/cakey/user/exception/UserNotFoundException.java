package bungae.thunder.cakey.user.exception;

import bungae.thunder.cakey.common.error.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) { super(message); }

}