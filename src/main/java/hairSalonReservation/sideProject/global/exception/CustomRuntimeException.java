package hairSalonReservation.sideProject.global.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public CustomRuntimeException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
