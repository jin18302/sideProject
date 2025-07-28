package hairSalonReservation.sideProject.common.exception;

public class BadRequestException extends CustomRuntimeException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
