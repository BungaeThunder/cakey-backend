package bungae.thunder.cakey.user.exception;

import bungae.thunder.cakey.common.error.exception.EntityNotFoundException;
import bungae.thunder.cakey.common.error.exception.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long target) { super("userId " + target + " is not found", ErrorCode.USER_NOT_FOUND); }

}