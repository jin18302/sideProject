package hairSalonReservation.sideProject.domain.reservation.repository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ReservationRepositoryCustom {

    boolean existByDesignerIdAndSlot(Long designerId, LocalDate date, LocalTime time);
}
