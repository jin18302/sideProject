package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.ServiceMenuCategoryMapperRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuCategoryMapperResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategory;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategoryMapper;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryMapperRepository;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryMapperRepositoryCustomImpl;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuCategoryRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
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

    @Transactional
    public List<ServiceMenuCategoryMapperResponse> createServiceCategoryMapper(Long designerId, ServiceMenuCategoryMapperRequest request) {

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        List<ServiceMenuCategory> serviceMenuCategory = serviceMenuCategoryRepository
                .findAllByIdInAndIsDeletedFalse(new ArrayList<>(request.serviceCategoryIdSet()));

        if (request.serviceCategoryIdSet().size() != serviceMenuCategory.size()) {
            throw new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND);
        }

        List<ServiceMenuCategoryMapper> serviceMenuCategoryMapperList = serviceMenuCategory.stream()
                .map(c -> ServiceMenuCategoryMapper.of(c, designer)).toList();

        serviceMenuCategoryMapperRepository.saveAll(serviceMenuCategoryMapperList);
        return serviceMenuCategoryMapperList.stream().map(ServiceMenuCategoryMapperResponse::from).toList();
    }

    public List<ServiceMenuCategoryMapperResponse> readByDesignerId(Long designerId){

        return serviceMenuCategoryMapperRepositoryCustom.findByDesignerId(designerId).stream()
                .map(ServiceMenuCategoryMapperResponse::from).toList();
    }

    @Transactional
    public List<ServiceMenuCategoryMapperResponse> updateServiceCategoryMapper(Long designerId, ServiceMenuCategoryMapperRequest request) {

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        List<Long> existingCategoryIdList = serviceMenuCategoryMapperRepositoryCustom.findByDesignerId(designer.getId())
                .stream().map(ServiceMenuCategoryMapper::getServiceMenuCategory)
                .map(ServiceMenuCategory::getId).toList();

        List<Long> toDeleteIdList = existingCategoryIdList.stream()
                .filter(i -> !request.serviceCategoryIdSet().contains(i)).toList();// 삭제대상

        List<Long> toAddIdList = new ArrayList<>(request.serviceCategoryIdSet()).stream()
                .filter(i -> !existingCategoryIdList.contains(i)).toList();

        serviceMenuCategoryMapperRepositoryCustom.deleteByDesignerIdAndCategoryIdIn(designerId, toDeleteIdList);
        List<ServiceMenuCategory> serviceMenuCategoryList = serviceMenuCategoryRepository.findAllByIdInAndIsDeletedFalse(toAddIdList);

        if (toAddIdList.size() != serviceMenuCategoryList.size()) {
            throw new NotFoundException(ErrorCode.SERVICE_MENU_CATEGORY_NOTFOUND);
        }
        List<ServiceMenuCategoryMapper> serviceMenuCategoryMapperList = serviceMenuCategoryList.stream()
                .map(c -> ServiceMenuCategoryMapper.of(c, designer)).toList();

        serviceMenuCategoryMapperRepository.saveAll(serviceMenuCategoryMapperList);
        return serviceMenuCategoryMapperList.stream().map(ServiceMenuCategoryMapperResponse::from).toList();
    }
}
