package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.common.exception.ErrorCode;
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

        if(!shopRepository.existsById(shopId)){throw new NotFoundException(ErrorCode.SHOP_NOT_FOUND);}
        if(!userRepository.existsById(userId)){throw new NotFoundException(ErrorCode.USER_NOT_FOUND);}

        Shop shop = shopRepository.getReferenceById(shopId);
        User user = userRepository.getReferenceById(userId);

        ShopFavorite shopFavorite = ShopFavorite.of(user, shop);
        shopFavoriteRepository.save(shopFavorite);

        return CreateShopFavoriteResponse.from(shopFavorite);
    }

    @Transactional
    public void deleteShopFavorite(Long shopFavoriteId, Long userId){

        if(!shopFavoriteRepository.existsById(shopFavoriteId)){throw new NotFoundException(ErrorCode.SHOP_FAVORITE_NOT_FOUND);}
        shopFavoriteRepository.deleteById(shopFavoriteId);
    }
}
