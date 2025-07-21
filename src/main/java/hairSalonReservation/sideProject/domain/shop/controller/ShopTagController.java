package hairSalonReservation.sideProject.domain.shop.controller;

import hairSalonReservation.sideProject.domain.shop.service.ShopTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopTagController {

    private final ShopTagService shopTagService;
}
