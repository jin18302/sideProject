package hairSalonReservation.sideProject.common.exception;

public class ForbiddenException extends CustomRuntimeException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
