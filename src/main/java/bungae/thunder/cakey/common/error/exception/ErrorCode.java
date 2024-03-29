package bungae.thunder.cakey.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_INPUT_TYPE(400, "C005", "Invalid Type Value"),
    ACCESS_DENIED(403, "C006", "Access is Denied"),
    ;
    private final String code;
    private final String type;
    private int status;

    ErrorCode(final int status, final String code, final String type) {
        this.status = status;
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
