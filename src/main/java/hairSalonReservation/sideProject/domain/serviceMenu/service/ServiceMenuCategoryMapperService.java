package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.ServiceMenuCategoryMapperRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuCategoryMapperResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceCategoryMapper;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryMapperRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryMapperRepositoryCustomImpl;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryRepository;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import hairSalonReservation.sideProject.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceMenuCategoryMapperService {

    private final ServiceMenuCategoryMapperRepository serviceMenuCategoryMapperRepository;
    private final ServiceMenuCategoryMapperRepositoryCustomImpl serviceMenuCategoryMapperRepositoryCustom;
    private final DesignerRepository designerRepository;
    private final ServiceMenuCategoryRepository serviceMenuCategoryRepository;

    @CheckRole("ADMIN")
    @Transactional
    public List<ServiceMenuCategoryMapperResponse> createServiceCategoryMapper(Long userId, Long designerId, ServiceMenuCategoryMapperRequest request) {

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        Long shopOwnerId = designer.getShop().getUser().getId();
        if(!userId.equals(shopOwnerId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        List<ServiceMenuCategory> serviceMenuCategory = serviceMenuCategoryRepository
                .findAllByIdInAndIsDeletedFalse(new ArrayList<>(request.serviceCategoryIdSet()));

        if (request.serviceCategoryIdSet().size() != serviceMenuCategory.size()) {
            throw new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND);
        }

        List<ServiceCategoryMapper> serviceMenuCategoryMapperList = serviceMenuCategory.stream()
                .map(c -> ServiceCategoryMapper.of(c, designer)).toList();

        serviceMenuCategoryMapperRepository.saveAll(serviceMenuCategoryMapperList);
        return serviceMenuCategoryMapperList.stream().map(ServiceMenuCategoryMapperResponse::from).toList();
    }

    public List<ServiceMenuCategoryMapperResponse> readByDesignerId(Long designerId){

        return serviceMenuCategoryMapperRepositoryCustom.findByDesignerId(designerId).stream()
                .map(ServiceMenuCategoryMapperResponse::from).toList();
    }

    @CheckRole("ADMIN")
    @Transactional
    public List<ServiceMenuCategoryMapperResponse> updateServiceCategoryMapper(Long userId, Long designerId, ServiceMenuCategoryMapperRequest request) {

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        Long shopOwnerId = designer.getShop().getUser().getId();
        if(!userId.equals(shopOwnerId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        List<Long> existingCategoryIdList = serviceMenuCategoryMapperRepositoryCustom.findByDesignerId(designer.getId())
                .stream().map(ServiceCategoryMapper::getServiceMenuCategory)
                .map(ServiceMenuCategory::getId).toList();

        List<Long> toDeleteIdList = existingCategoryIdList.stream()
                .filter(i -> !request.serviceCategoryIdSet().contains(i)).toList();// 삭제대상

        List<Long> toAddIdList = new ArrayList<>(request.serviceCategoryIdSet()).stream()
                .filter(i -> !existingCategoryIdList.contains(i)).toList();

        serviceMenuCategoryMapperRepositoryCustom.deleteByDesignerIdAndCategoryIdIn(designerId, toDeleteIdList);
        List<ServiceMenuCategory> serviceMenuCategoryList = serviceMenuCategoryRepository.findAllByIdInAndIsDeletedFalse(toAddIdList);

        if (toAddIdList.size() != serviceMenuCategoryList.size()) {throw new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND);}

        List<ServiceCategoryMapper> serviceMenuCategoryMapperList = serviceMenuCategoryList.stream()
                .map(c -> ServiceCategoryMapper.of(c, designer)).toList();

        serviceMenuCategoryMapperRepository.saveAll(serviceMenuCategoryMapperList);
        return serviceMenuCategoryMapperList.stream().map(ServiceMenuCategoryMapperResponse::from).toList();
    }
}
