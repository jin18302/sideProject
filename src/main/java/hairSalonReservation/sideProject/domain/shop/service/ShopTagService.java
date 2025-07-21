package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopTagRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopTagResponse;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;
import hairSalonReservation.sideProject.domain.shop.repository.ShopTagRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopTagService {

    private final ShopTagRepository shopTagRepository;

    //관리자 전용
    @Transactional
    public ShopTagResponse createShopTag(CreateShopTagRequest request){

        ShopTag shopTag = ShopTag.from(request.name());
        shopTagRepository.save(shopTag);
        return ShopTagResponse.from(shopTag);
    }

    @Transactional
    public void deleteShopTag(Long shopTagId){

        ShopTag shopTag = shopTagRepository.findByIdAndIsDeletedFalse(shopTagId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_TAG_NOT_FOUND));

        shopTag.delete();
    }
}
