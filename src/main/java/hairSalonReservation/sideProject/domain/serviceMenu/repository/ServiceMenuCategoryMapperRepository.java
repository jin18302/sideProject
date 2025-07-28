package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceCategoryMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMenuCategoryMapperRepository extends JpaRepository<ServiceCategoryMapper, Long> {
}
