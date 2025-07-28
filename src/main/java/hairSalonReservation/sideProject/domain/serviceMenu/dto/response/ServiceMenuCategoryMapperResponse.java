package hairSalonReservation.sideProject.domain.serviceMenu.dto.response;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceCategoryMapper;

public record ServiceMenuCategoryMapperResponse(Long id, String name) {

    public static ServiceMenuCategoryMapperResponse from(ServiceCategoryMapper categoryMapper){
        return new ServiceMenuCategoryMapperResponse(categoryMapper.getId(), categoryMapper.getServiceMenuCategory().getName());
    }
}
