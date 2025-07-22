package hairSalonReservation.sideProject.domain.serviceMenu.dto.response;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategoryMapper;

public record ServiceMenuCategoryMapperResponse(Long id, String name) {

    public static ServiceMenuCategoryMapperResponse from(ServiceMenuCategoryMapper categoryMapper){
        return new ServiceMenuCategoryMapperResponse(categoryMapper.getId(), categoryMapper.getServiceMenuCategory().getName());
    }
}
