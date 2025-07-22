package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceMenuCategoryRepository extends JpaRepository<ServiceMenuCategory, Long> {

    boolean existsByName(String name);

    List<ServiceMenuCategory> findAllByIsDeletedFalse();

    List<ServiceMenuCategory> findAllByIdInAndIsDeletedFalse(List<Long> serviceMenuCategoryIdList);


}
