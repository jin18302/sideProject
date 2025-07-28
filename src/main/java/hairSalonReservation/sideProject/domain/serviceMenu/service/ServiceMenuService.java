package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.UpdateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceCategoryMapper;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryMapperRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepositoryCustomImpl;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.ForbiddenException;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceMenuService {

    private final ServiceMenuRepository serviceMenuRepository;
    private final ServiceMenuRepositoryCustomImpl serviceMenuRepositoryCustom;
    private final ServiceMenuCategoryMapperRepository serviceMenuCategoryMapperRepository;

    @CheckRole("OWNER")
    @Transactional
    public ServiceMenuResponse createServiceMenu(Long serviceCategoryMapperId, CreateServiceMenuRequest request){

        ServiceCategoryMapper serviceMenuCategoryMapper = serviceMenuCategoryMapperRepository.findById(serviceCategoryMapperId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND));

        ServiceMenu serviceMenu = ServiceMenu.of(
                serviceMenuCategoryMapper,
                request.name(),
                request.price(),
                request.introduction()
        );

        serviceMenuRepository.save(serviceMenu);
        return ServiceMenuResponse.from(serviceMenu);
    }

    public List<ServiceMenuResponse> readAllByDesignerServiceCategory(Long serviceCategoryMapperId){

        return serviceMenuRepositoryCustom.findByServiceCategoryMapperId(serviceCategoryMapperId)
                .stream().map(ServiceMenuResponse::from).toList();
    }

    @CheckRole("OWNER")
    @Transactional
    public ServiceMenuResponse updateServiceMenu(Long userId, Long serviceMenuId, UpdateServiceMenuRequest request){

        ServiceMenu menu = serviceMenuRepository.findById(serviceMenuId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_NOT_FOUND));

        Long shopOwnerId = menu.getServiceMenuCategoryMapper().getDesigner().getShop().getId();
        if(!userId.equals(shopOwnerId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        menu.update(
                request.name(),
                request.price(),
                request.introduction()
        );

        return ServiceMenuResponse.from(menu);
    }

    @CheckRole("OWNER")
    @Transactional
    public void deleteServiceMenu(Long userId, Long serviceMenuId){

        ServiceMenu menu = serviceMenuRepository.findById(serviceMenuId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_NOT_FOUND));

        Long shopOwnerId = menu.getServiceMenuCategoryMapper().getDesigner().getShop().getId();
        if(!userId.equals(shopOwnerId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        menu.delete();
    }
}
