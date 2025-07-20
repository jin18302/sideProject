package hairSalonReservation.sideProject.domain.shopTag.controller;

import hairSalonReservation.sideProject.domain.shopTag.service.ShopTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopTagController {

    private final ShopTagService shopTagService;
}
