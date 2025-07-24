package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopTagRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopTagResponse;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;
import hairSalonReservation.sideProject.domain.shop.repository.ShopTagRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopTagService {

    private final ShopTagRepository shopTagRepository;

    @CheckRole("ADMIN")
    @Transactional
    public ShopTagResponse createShopTag(CreateShopTagRequest request){

        ShopTag shopTag = ShopTag.from(request.name());
        shopTagRepository.save(shopTag);
        return ShopTagResponse.from(shopTag);
    }

    public List<ShopTagResponse> readAllShopTag(){

        List<ShopTag> shopTagList = shopTagRepository.findAllByIsDeletedFalse();
        return shopTagList.stream().map(ShopTagResponse::from).toList();
    }

    @CheckRole("ADMIN")
    @Transactional
    public void deleteShopTag(Long shopTagId){

        ShopTag shopTag = shopTagRepository.findByIdAndIsDeletedFalse(shopTagId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_TAG_NOT_FOUND));

        shopTag.delete();
    }
}
