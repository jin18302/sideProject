package hairSalonReservation.sideProject.common.exception;

public class NotFoundException extends CustomRuntimeException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
