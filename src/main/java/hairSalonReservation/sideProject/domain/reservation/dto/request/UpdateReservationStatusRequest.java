package hairSalonReservation.sideProject.domain.reservation.dto.request;

import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;
import jakarta.validation.constraints.NotEmpty;

public record UpdateReservationStatusRequest(@NotEmpty  ReservationStatus status) {
}
