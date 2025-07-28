package hairSalonReservation.sideProject.domain.designer.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.designer.dto.request.CreateDesignerRequest;
import hairSalonReservation.sideProject.domain.designer.dto.request.UpdateDesignerRequest;
import hairSalonReservation.sideProject.domain.designer.dto.response.DesignerDetailResponse;
import hairSalonReservation.sideProject.domain.designer.dto.response.DesignerSummaryResponse;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepositoryCustomImpl;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepository;
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
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final DesignerRepositoryCustomImpl designerRepositoryCustomImpl;
    private final ShopRepository shopRepository;


    @CheckRole("OWNER")
    @Transactional
    public DesignerDetailResponse createDesigner(Long shopId, Long userId, CreateDesignerRequest request){

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_NOT_FOUND));
        Long shopOwnerId = shop.getUser().getId();
        if(!shopOwnerId.equals(userId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        Designer designer = Designer.of(
                shop,
                request.name(),
                request.profileImage(),
                request.introduction(),
                JsonHelper.toJson(request.dayOffWeekdayList()),
                JsonHelper.toJson(request.imageUriList()),
                JsonHelper.toJson(request.snsUriList())
        );

        designerRepository.save(designer);
        return DesignerDetailResponse.from(designer);
    }

    public List<DesignerSummaryResponse> readByShop(Long shopId){

        return designerRepositoryCustomImpl.findByShopId(shopId);
    }

    public DesignerDetailResponse readById(Long designerId){

        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        return DesignerDetailResponse.from(designer);
    }

    @CheckRole("OWNER")
    @Transactional
    public DesignerDetailResponse updateDesigner(Long userId, Long designerId,  UpdateDesignerRequest request){

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        Long shopOwnerId = designer.getShop().getUser().getId();

        if(shopOwnerId != userId){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        designer.update(
                request.name(),
                request.profileImage(),
                request.introduction(),
                JsonHelper.toJson(request.imageUriList()),
                JsonHelper.toJson(request.snsUriList())
        );

        return DesignerDetailResponse.from(designer);
    }

    @CheckRole("OWNER")
    @Transactional
    public void deleteDesigner(Long userId, Long designerId){

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        if(designer.getShop().getUser().getId() != userId){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        designer.delete();
    }
}
