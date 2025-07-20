package hairSalonReservation.sideProject.domain.calendar.repository;

import hairSalonReservation.sideProject.domain.calendar.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
