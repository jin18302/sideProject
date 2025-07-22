package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategoryMapper;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryMapperRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceMenuService {

    private final ServiceMenuRepository serviceMenuRepository;
    private final ServiceMenuCategoryMapperRepository serviceMenuCategoryMapperRepository;

    @Transactional
    public ServiceMenuResponse createServiceMenu(Long serviceCategoryMapperId, CreateServiceMenuRequest request){

        ServiceMenuCategoryMapper serviceMenuCategoryMapper = serviceMenuCategoryMapperRepository.findById(serviceCategoryMapperId)
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
}
