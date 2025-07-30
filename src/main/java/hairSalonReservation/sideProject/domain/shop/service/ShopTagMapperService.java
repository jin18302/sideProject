package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTagMapper;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepositoryCustom;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepositoryCustomImpl;
import hairSalonReservation.sideProject.domain.shop.repository.ShopTagRepository;
import hairSalonReservation.sideProject.common.exception.BadRequestException;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CheckRole("OWNER")
@Service
@RequiredArgsConstructor
public class ShopTagMapperService {

    private final ShopTagRepository shopTagRepository;
    private final ShopRepositoryCustomImpl shopRepositoryCustom;

    @Transactional
    public void createShopTagMapper(Shop shop, List<Long> shopTagIdList) {// TODO : 위치 고려

        List<ShopTag> shopTagList = shopTagRepository.findAllByIdInAndIsDeletedFalse(shopTagIdList);

        if (shopTagIdList.size() != shopTagList.size()) {throw new BadRequestException(ErrorCode.SHOP_TAG_NOT_FOUND);}
        shopTagList.forEach(s -> ShopTagMapper.of(s, shop));
    }

    @Transactional
    public void updateShopTagMapper(Long userId, Shop shop, List<Long> shopTagIdList ){

        Long shopOwnerId = shopRepositoryCustom.findShopOwnerIdByShopId(shop.getId());
        if(!userId.equals(shopOwnerId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        List<Long> existingTagSet = shop.getShopTagMapperList().stream().map(ShopTagMapper::getShopTag).map(ShopTag::getId).toList();
        List<Long> newTagSet = shopTagRepository.findAllByIdInAndIsDeletedFalse(shopTagIdList).stream().map(ShopTag::getId).toList();

        List<Long> toRemoveTagIdList = existingTagSet.stream().filter(id -> !newTagSet.contains(id)).toList();
        List<Long> toAddTagIdList = newTagSet.stream().filter(id -> !existingTagSet.contains(id)).toList();

        shop.getShopTagMapperList().removeIf(t -> toRemoveTagIdList.contains(t.getShopTag().getId()));
        createShopTagMapper(shop, toAddTagIdList);
    }
}
