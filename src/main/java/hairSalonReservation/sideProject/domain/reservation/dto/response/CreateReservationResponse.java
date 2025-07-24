package hairSalonReservation.sideProject.domain.reservation.dto.response;

import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;

public record CreateReservationResponse(
        Long id,
        ServiceMenuResponse serviceMenu,
        Long designerId,
        Long userId,
        ReservationStatus reservationStatus
) {

    public static CreateReservationResponse from(Reservation reservation){
        return new CreateReservationResponse(
                reservation.getId(),
                ServiceMenuResponse.from(reservation.getServiceMenu()),
                reservation.getDesigner().getId(),
                reservation.getUser().getId(),
                reservation.getReservationStatus()
        );
    }
}
