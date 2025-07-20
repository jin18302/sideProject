package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMenuRepository extends JpaRepository<ServiceMenu, Long> {
}
