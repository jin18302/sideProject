package hairSalonReservation.sideProject.domain.shop.controller;


import hairSalonReservation.sideProject.domain.shopTag.service.ShopTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopTagService shopTagService;
}
