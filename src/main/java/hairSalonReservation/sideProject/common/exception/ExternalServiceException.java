package hairSalonReservation.sideProject.common.exception;


public class ExternalServiceException extends CustomRuntimeException {

    public ExternalServiceException(ErrorCode errorCode) {
        super(errorCode);
    }
}