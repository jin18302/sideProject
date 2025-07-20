package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategoryMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMenuCategoryMapperRepository extends JpaRepository<ServiceMenuCategoryMapper, Long> {
}
