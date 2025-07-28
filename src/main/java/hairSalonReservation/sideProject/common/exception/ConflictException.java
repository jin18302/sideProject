package hairSalonReservation.sideProject.common.exception;

public class ConflictException extends CustomRuntimeException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
