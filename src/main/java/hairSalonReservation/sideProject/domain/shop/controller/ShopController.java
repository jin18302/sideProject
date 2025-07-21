package hairSalonReservation.sideProject.domain.shop.controller;

import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.request.UpdateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopDetailResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import hairSalonReservation.sideProject.domain.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/shops")
    public ResponseEntity<CreateShopResponse> createShop(@RequestBody CreateShopRequest createShopRequest, @RequestAttribute("userId") Long userId) {

        CreateShopResponse createShopResponse = shopService.createShop(createShopRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createShopResponse);
    }

    @GetMapping("/shops")
    public ResponseEntity<Page<ShopSummaryResponse>> readByFilter(
            @RequestParam(required = false, name = "area") String area,
            @RequestParam(required = false, name = "tagList") List<String> tagList,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size,
            @RequestParam(required = false, name = "page", defaultValue = "1") int page,
            @RequestParam(required = false, name = "sort") String sort
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString("DESC"), sort));
        Page<ShopSummaryResponse> shopResponsePage = shopService.readByFilter(pageable, area, tagList);
        return ResponseEntity.ok(shopResponsePage);
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShopDetailResponse> readShopDetail(@PathVariable("shopId") Long shopId){

        ShopDetailResponse shopDetailResponse = shopService.readShopDetail(shopId);
        return ResponseEntity.ok(shopDetailResponse);
    }

    @PatchMapping("/shops/{shopId}")
    public ResponseEntity<ShopDetailResponse> updateShop(@RequestAttribute("userId") Long userId,
                                                         @PathVariable("shopId") Long shopId,
                                                         @RequestBody UpdateShopRequest updateShopRequest){

        ShopDetailResponse shopDetailResponse = shopService.updateShop(userId, shopId, updateShopRequest);
        return ResponseEntity.ok(shopDetailResponse);
    }
}
