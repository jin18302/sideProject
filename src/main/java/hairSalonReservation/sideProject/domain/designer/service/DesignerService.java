package hairSalonReservation.sideProject.domain.designer.service;

import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.designer.dto.request.CreateDesignerRequest;
import hairSalonReservation.sideProject.domain.designer.dto.response.DesignerResponse;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepository;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.ForbiddenException;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final ShopRepository shopRepository;

    @Transactional // TODO : 디자이너가 생성됨과 동시에 해당 디자이너의 캘린더가 자동으로 생성되어야함
    public DesignerResponse createDesigner(Long shopId, Long userId, CreateDesignerRequest request){

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_NOT_FOUND));
        if(shop.getUser().getId() != userId){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        Designer designer = Designer.of(
                shop,
                request.name(),
                request.introduction(),
                JsonHelper.toJson(request.imageUriList()),
                JsonHelper.toJson(request.snsUriList())
        );

        designerRepository.save(designer);
        return DesignerResponse.from(designer);
    }
}
