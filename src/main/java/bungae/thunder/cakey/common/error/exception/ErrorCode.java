package bungae.thunder.cakey.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Global
    INVALID_INPUT_VALUE(400, "G001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "G002", "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "G003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "G004", "Server Error"),
    INVALID_TYPE_VALUE(400, "G005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "G006", "Access is Denied"),

    // User
    USER_NOT_FOUND(400, "U001", "유저가 존재하지 않습니다."),

    // Cake
    CAKE_NOT_FOUND(400, "C001", "케이크가 존재하지 않습니다."),

    // Report
    REPORT_NOT_FOUND(400, "R001", "레포트가 존재하지 않습니다."),

    // Message
    MESSAGE_NOT_FOUND(400, "M001", "메시지가 존재하지 않습니다.")

    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}