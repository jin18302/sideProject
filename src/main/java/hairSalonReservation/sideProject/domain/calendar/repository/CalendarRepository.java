package hairSalonReservation.sideProject.domain.calendar.repository;

import hairSalonReservation.sideProject.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
