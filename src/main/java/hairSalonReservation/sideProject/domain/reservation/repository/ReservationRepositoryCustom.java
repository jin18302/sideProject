package hairSalonReservation.sideProject.domain.reservation.repository;

import hairSalonReservation.sideProject.domain.reservation.dto.response.ReservationResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepositoryCustom {

    boolean existByDesignerIdAndSlot(Long designerId, LocalDate date, LocalTime time);

    List<ReservationResponse> findByDesignerIdAndDate(Long designerId, LocalDate date);
}
