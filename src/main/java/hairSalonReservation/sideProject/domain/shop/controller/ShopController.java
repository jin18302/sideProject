package hairSalonReservation.sideProject.domain.shop.controller;


import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopResponse;
import hairSalonReservation.sideProject.domain.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/shops")
    public ResponseEntity<CreateShopResponse> createShop(@RequestBody CreateShopRequest createShopRequest, @RequestAttribute("userId") Long userId){

        CreateShopResponse createShopResponse = shopService.createShop(createShopRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createShopResponse);
    }
}
