package bungae.thunder.cakey.common.error.exception;

public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(String runTimeMessage, ErrorCode errorCode) {
        super(runTimeMessage);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}