package hairSalonReservation.sideProject.domain.designer.repository;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignerRepository extends JpaRepository<Designer, Long> {

    Optional<Designer> findByIdAndIsDeletedFalse(Long designerId);
}
