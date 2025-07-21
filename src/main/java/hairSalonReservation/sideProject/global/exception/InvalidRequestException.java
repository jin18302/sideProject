package hairSalonReservation.sideProject.global.exception;

public class InvalidRequestException extends CustomRuntimeException {
    public InvalidRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
