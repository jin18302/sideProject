package hairSalonReservation.sideProject.domain.reservation.repository;

import hairSalonReservation.sideProject.domain.reservation.entity.ScheduleBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleBlockRepository extends JpaRepository<ScheduleBlock, Long> {
}
