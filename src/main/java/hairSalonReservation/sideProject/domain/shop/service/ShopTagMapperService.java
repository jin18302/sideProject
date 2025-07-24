package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTagMapper;
import hairSalonReservation.sideProject.domain.shop.repository.ShopTagRepository;
import hairSalonReservation.sideProject.global.exception.BadRequestException;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopTagMapperService {

    private final ShopTagRepository shopTagRepository;

    public void createShopTagMapper(Shop shop, List<Long> shopTagIdList) {// TODO : 위치 고려

        List<ShopTag> shopTagList = shopTagRepository.findAllByIdInAndIsDeletedFalse(shopTagIdList);

        if (shopTagIdList.size() != shopTagList.size()) {throw new BadRequestException(ErrorCode.SHOP_TAG_NOT_FOUND);}
        shopTagList.forEach(s -> ShopTagMapper.of(s, shop));
    }

    public void updateShopTagMapper(Shop shop, List<Long> shopTagIdList ){
        List<Long> existingTagSet = shop.getShopTagMapperList().stream().map(ShopTagMapper::getShopTag).map(ShopTag::getId).toList();
        List<Long> newTagSet = shopTagRepository.findAllByIdInAndIsDeletedFalse(shopTagIdList).stream().map(ShopTag::getId).toList();

        List<Long> toRemoveTagIdList = existingTagSet.stream().filter(id -> !newTagSet.contains(id)).toList();
        List<Long> toAddTagIdList = newTagSet.stream().filter(id -> !existingTagSet.contains(id)).toList();

        shop.getShopTagMapperList().removeIf(t -> toRemoveTagIdList.contains(t.getShopTag().getId()));
        createShopTagMapper(shop, toAddTagIdList);
    }
}
