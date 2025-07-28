package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceCategoryMapper;

import java.util.List;

public interface ServiceMenuCategoryMapperRepositoryCustom {

    List<ServiceCategoryMapper> findByDesignerId(Long designerId);

    void deleteByDesignerIdAndCategoryIdIn(Long designerId, List<Long> categoryIdList);
}
