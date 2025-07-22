package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceMenuCategoryRepository extends JpaRepository<ServiceMenuCategory, Long> {

    boolean existsByName(String name);

    List<ServiceMenuCategory> findAllByIsDeletedFalse();

}
