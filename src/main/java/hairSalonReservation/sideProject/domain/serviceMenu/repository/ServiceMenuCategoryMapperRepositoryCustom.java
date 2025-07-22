package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategoryMapper;

import java.util.List;

public interface ServiceMenuCategoryMapperRepositoryCustom {

    List<ServiceMenuCategoryMapper> findByDesignerId(Long designerId);

    void deleteByDesignerId(Long designerId);
}
