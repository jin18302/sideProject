package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuCategoryRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.UpdateServiceMenuCategoryRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuCategoryResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryRepository;
import hairSalonReservation.sideProject.global.exception.ConflictException;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceMenuCategoryService {

    private final ServiceMenuCategoryRepository serviceMenuCategoryRepository;

    @CheckRole("ADMIN")
    @Transactional
    public ServiceMenuCategoryResponse createServiceMenuCategory(CreateServiceMenuCategoryRequest request){

        if(serviceMenuCategoryRepository.existsByName(request.name())){
            throw new ConflictException(ErrorCode.DUPLICATE_CATEGORY_NAME);
        }

        ServiceMenuCategory serviceMenuCategory = ServiceMenuCategory.from(request.name());
        serviceMenuCategoryRepository.save(serviceMenuCategory);
        return ServiceMenuCategoryResponse.from(serviceMenuCategory);
    }

    public List<ServiceMenuCategoryResponse> readAll(){

        return serviceMenuCategoryRepository.findAllByIsDeletedFalse().stream().map(ServiceMenuCategoryResponse::from).toList();
    }

    @CheckRole("ADMIN")
    @Transactional
    public ServiceMenuCategoryResponse updateServiceMenuCategory(Long serviceMenuCategoryId, UpdateServiceMenuCategoryRequest request){

        ServiceMenuCategory serviceMenuCategory = serviceMenuCategoryRepository.findById(serviceMenuCategoryId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND));

        if(serviceMenuCategoryRepository.existsByName(request.name())){
            throw new ConflictException(ErrorCode.DUPLICATE_CATEGORY_NAME);
        }

        serviceMenuCategory.update(request.name());
        return ServiceMenuCategoryResponse.from(serviceMenuCategory);
    }

    @CheckRole("ADMIN")
    @Transactional
    public void deleteServiceMenuCategory(Long serviceMenuCategoryId){

        ServiceMenuCategory serviceMenuCategory = serviceMenuCategoryRepository.findById(serviceMenuCategoryId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND));

        serviceMenuCategory.delete();
    }
}
