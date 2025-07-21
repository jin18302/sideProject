package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

//    public CreateShopResponse createShop(CreateShopRequest request){
//
//       String stringImageUriList = JsonHelper.toJson(request.imageUrlList());
//       String stringSnsUriList = JsonHelper.toJson(request.snsUriList());
//
//
//    }



}
