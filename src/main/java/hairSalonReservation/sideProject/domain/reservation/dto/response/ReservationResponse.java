package hairSalonReservation.sideProject.domain.reservation.dto.response;

import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(
        Long id,
        String serviceMenuName,
        String designerName,
        ReservationStatus reservationStatus,
        LocalDate date,
        LocalTime time
) {

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getServiceMenu().getName(),
                reservation.getDesigner().getName(),
                reservation.getReservationStatus(),
                reservation.getDate(),
                reservation.getTime()
        );
    }
}
