package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.request.UpdateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopDetailResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.entity.ShopStatus;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;
import hairSalonReservation.sideProject.domain.shop.entity.ShopTagMapper;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepository;
import hairSalonReservation.sideProject.domain.shop.repository.ShopTagRepository;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.ForbiddenException;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ShopTagRepository shopTagRepository;

    @Transactional
    public CreateShopResponse createShop(CreateShopRequest request, Long userId) {

        if (!userRepository.existsById(userId)) {throw new NotFoundException(ErrorCode.USER_NOT_FOUND);}
        User user = userRepository.getReferenceById(userId);

        String stringImageUriList = JsonHelper.toJson(request.imageUrlList());
        String stringSnsUriList = JsonHelper.toJson(request.snsUriList());

        Shop shop = Shop.of(
                user,
                request.name(),
                request.businessId(),
                request.address(),
                request.phoneNumber(),
                request.openTime(),
                request.endTime(),
                request.introduction(),
                stringImageUriList,
                stringSnsUriList,
                request.openDate()
        );

        shopRepository.save(shop);

        createShopTagMapper(shop, request);
        return CreateShopResponse.from(shop);
    }

    public Page<ShopSummaryResponse> readByFilter(Pageable pageable, String area, List<String> tagList){
        return null;
    }

    public ShopDetailResponse readShopDetail(Long shopId){

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_NOT_FOUND));
        return ShopDetailResponse.from(shop);
    }

    @Transactional
    public ShopDetailResponse updateShop(Long userId, Long shopId, UpdateShopRequest updateShopRequest){

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_NOT_FOUND));
        if(!userId.equals(shop.getUser().getId())){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        ShopStatus shopStatus = ShopStatus.of(updateShopRequest.shopStatus());

        shop.update(
                updateShopRequest.name(),
                updateShopRequest.businessId(),
                updateShopRequest.address(),
                updateShopRequest.phoneNumber(),
                updateShopRequest.openTime(),
                updateShopRequest.endTime(),
                updateShopRequest.introduction(),
                JsonHelper.toJson(updateShopRequest.imageUrlList()),
                JsonHelper.toJson(updateShopRequest.snsUriList()),
                updateShopRequest.openDate(),
                shopStatus
                );

        return ShopDetailResponse.from(shop);
    }

    @Transactional
    public void deleteShop(Long userId, Long shopId){

        Shop shop = shopRepository.findByIdAndIsDeletedFalse(shopId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_NOT_FOUND));
        if(userId != shop.getUser().getId()){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        shop.delete();
    }

    private void createShopTagMapper(Shop shop, CreateShopRequest request){

        List<ShopTag> shopTagList = shopTagRepository.findAllByIsDeletedFalse();

        if(request.shopTagList().size() != shopTagList.size()){throw new RuntimeException();}
        shopTagList.forEach(s -> ShopTagMapper.of(s, shop));
    }
}
