package hairSalonReservation.sideProject.common.exception;

public class InvalidRequestException extends CustomRuntimeException {
    public InvalidRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
