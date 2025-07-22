package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuCategoryRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuCategoryResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryRepository;
import hairSalonReservation.sideProject.global.exception.ConflictException;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceMenuCategoryService {

    private final ServiceMenuCategoryRepository serviceMenuCategoryRepository;

    @Transactional
    public ServiceMenuCategoryResponse createServiceMenuCategory(CreateServiceMenuCategoryRequest request){

        if(!serviceMenuCategoryRepository.existsByName(request.name())){throw new ConflictException(ErrorCode.DUPLICATE_CATEGORY_NAME);}

        ServiceMenuCategory serviceMenuCategory = ServiceMenuCategory.from(request.name());
        serviceMenuCategoryRepository.save(serviceMenuCategory);
        return ServiceMenuCategoryResponse.from(serviceMenuCategory);
    }


}
