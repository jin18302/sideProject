package hairSalonReservation.sideProject.domain.shop.controller;

import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopFavoriteResponse;
import hairSalonReservation.sideProject.domain.shop.service.ShopFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopFavoriteController {

    private final ShopFavoriteService shopFavoriteService;

    @PostMapping("/shops/{shopId}")
    public ResponseEntity<CreateShopFavoriteResponse> createShopFavorite(@PathVariable(name = "shopId")Long shopId,
                                                                         @RequestAttribute("userId") Long userId
    ){
        CreateShopFavoriteResponse createShopFavoriteResponse = shopFavoriteService.createShopFavorite(shopId, userId);
        return ResponseEntity.ok(createShopFavoriteResponse);
    }

    @DeleteMapping("shop-favorite/{shopFavoriteId}")
    public ResponseEntity<Void> deleteShopFavorite(@PathVariable(name = "shopFavoriteId") Long shopFavoriteId,
                                                   @RequestAttribute("userId") Long userId
    ){
        shopFavoriteService.deleteShopFavorite(shopFavoriteId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
