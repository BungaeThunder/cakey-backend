package bungae.thunder.cakey.common.error;

import bungae.thunder.cakey.common.error.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String type;
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    private ErrorResponse(
        final ErrorCode code, final String message, final List<FieldError> errors) {
        this.type = code.getType();
        this.message = message;
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code, final String message) {
        this.type = code.getType();
        this.message = message;
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
        this.code = code.getCode();
    }

    public static ErrorResponse of(
        final ErrorCode code, final String message, final BindingResult bindingResult) {
        return new ErrorResponse(code, message, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final ErrorCode code, final String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(
        final ErrorCode code, final String message, final List<FieldError> errors) {
        return new ErrorResponse(code, message, errors);
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ErrorResponse.FieldError> errors =
            ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.INVALID_INPUT_TYPE, e.getMessage(), errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {

        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(
            final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                bindingResult.getFieldErrors();
            return fieldErrors.stream()
                .map(
                    error ->
                        new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null
                                ? ""
                                : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                .collect(Collectors.toList());
        }
    }
}
