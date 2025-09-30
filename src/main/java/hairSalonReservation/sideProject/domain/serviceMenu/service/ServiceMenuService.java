package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.UpdateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepositoryCustomImpl;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import hairSalonReservation.sideProject.common.exception.NotFoundException;
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
    private final DesignerRepository designerRepository;

    @CheckRole("OWNER")
    @Transactional
    public ServiceMenuResponse createServiceMenu(Long userId, Long designerId, CreateServiceMenuRequest request){

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(()->new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        Long ownerId = designer.getShop().getUser().getId();
        if(!ownerId.equals(userId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        ServiceMenu serviceMenu = ServiceMenu.of(
                designer,
                request.category(),
                request.name(),
                request.price(),
                request.introduction()
        );

        serviceMenuRepository.save(serviceMenu);
        return ServiceMenuResponse.from(serviceMenu);
    }

    public List<ServiceMenuResponse> readByDesignerAndCategory(Long designerId, String category){

        return serviceMenuRepositoryCustom.findByDesignerAndCategory(designerId, category)
                .stream().map(ServiceMenuResponse::from).toList();
    }

    @CheckRole("OWNER")
    @Transactional
    public ServiceMenuResponse updateServiceMenu(Long userId, Long serviceMenuId, UpdateServiceMenuRequest request){

        ServiceMenu menu = serviceMenuRepository.findById(serviceMenuId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_NOT_FOUND));

        Long shopOwnerId = menu.getDesigner().getShop().getId();
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

        Long shopOwnerId = menu.getDesigner().getShop().getId();
        if(!userId.equals(shopOwnerId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        menu.delete();
    }
}
