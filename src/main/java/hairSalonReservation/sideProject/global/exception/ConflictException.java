package hairSalonReservation.sideProject.global.exception;

public class ConflictException extends CustomRuntimeException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
