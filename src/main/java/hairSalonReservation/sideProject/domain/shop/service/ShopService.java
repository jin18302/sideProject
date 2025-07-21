package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopResponse;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.repository.ShopRepository;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

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
        return CreateShopResponse.from(shop);
    }


}
