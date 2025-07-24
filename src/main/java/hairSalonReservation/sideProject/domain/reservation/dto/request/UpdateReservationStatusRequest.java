package hairSalonReservation.sideProject.domain.reservation.dto.request;

import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;

public record UpdateReservationStatusRequest(ReservationStatus status) {
}
