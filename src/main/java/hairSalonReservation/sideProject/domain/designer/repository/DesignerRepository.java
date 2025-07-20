package hairSalonReservation.sideProject.domain.designer.repository;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignerRepository extends JpaRepository<Designer, Long> {
}
