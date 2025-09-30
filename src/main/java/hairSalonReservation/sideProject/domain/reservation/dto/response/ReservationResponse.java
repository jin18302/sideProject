package hairSalonReservation.sideProject.domain.reservation.dto.response;

import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;

public record ReservationResponse(
        Long id,
        Long serviceMenuId,
        Long designerId,
        Long userId,
        ReservationStatus reservationStatus
) {

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getServiceMenu().getId(),
                reservation.getDesigner().getId(),
                reservation.getUser().getId(),
                reservation.getReservationStatus()
        );
    }
}
