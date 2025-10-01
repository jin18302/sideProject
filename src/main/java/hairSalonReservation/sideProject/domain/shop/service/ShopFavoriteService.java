package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import hairSalonReservation.sideProject.common.exception.NotFoundException;
import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopFavoriteResponse;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.entity.ShopFavorite;
import hairSalonReservation.sideProject.domain.shop.repository.ShopFavoriteRepository;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepository;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopFavoriteService {

    private final ShopFavoriteRepository shopFavoriteRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public CreateShopFavoriteResponse createShopFavorite(Long shopId, Long userId){

        if(!userRepository.existsById(userId)){throw new NotFoundException(ErrorCode.USER_NOT_FOUND);}
        User user = userRepository.getReferenceById(userId);

        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_NOT_FOUND));

        ShopFavorite shopFavorite = ShopFavorite.of(user, shop);
        shopFavoriteRepository.save(shopFavorite);
        shop.increaseLikeCount(); // TODO: 비동기 가능한 로직임으로 변경여지

        return CreateShopFavoriteResponse.from(shopFavorite);
    }

    @Transactional
    public void deleteShopFavorite(Long shopFavoriteId, Long userId){

        ShopFavorite favorite = shopFavoriteRepository.findById(shopFavoriteId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHOP_FAVORITE_NOT_FOUND));

        if(!userId.equals(favorite.getUser().getId())){throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        favorite.getShop().decreaseLikeCount();//
        shopFavoriteRepository.deleteById(shopFavoriteId);
    }
}
