package bungae.thunder.cakey.common.error.exception;

public class EntityInvalidException extends BusinessException {

    public EntityInvalidException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }
}
