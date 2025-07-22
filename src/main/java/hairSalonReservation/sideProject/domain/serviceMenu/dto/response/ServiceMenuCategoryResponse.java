package hairSalonReservation.sideProject.domain.serviceMenu.dto.response;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;

public record ServiceMenuCategoryResponse(Long id, String name) {

    public static ServiceMenuCategoryResponse from(ServiceMenuCategory serviceMenuCategory){
        return new ServiceMenuCategoryResponse(serviceMenuCategory.getId(), serviceMenuCategory.getName());
    }
}
