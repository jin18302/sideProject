package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;

import java.util.List;

public interface ServiceMenuRepositoryCustom {

    List<ServiceMenu> findByDesignerAndCategory(Long designerId, String category);
}
