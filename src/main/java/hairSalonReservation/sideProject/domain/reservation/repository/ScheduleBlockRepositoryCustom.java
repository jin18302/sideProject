package hairSalonReservation.sideProject.domain.reservation.repository;

import hairSalonReservation.sideProject.domain.reservation.entity.ScheduleBlock;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleBlockRepositoryCustom {

    public Optional<ScheduleBlock> findByDesignerIdAndDate(Long designerId, LocalDate date);
}
