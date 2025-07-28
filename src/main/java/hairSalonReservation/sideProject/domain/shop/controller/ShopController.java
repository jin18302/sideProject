package hairSalonReservation.sideProject.domain.shop.controller;

import hairSalonReservation.sideProject.common.dto.CursorPageResponse;
import hairSalonReservation.sideProject.domain.shop.dto.request.CreateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.request.UpdateShopRequest;
import hairSalonReservation.sideProject.domain.shop.dto.response.CreateShopResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopDetailResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/shops")
    public ResponseEntity<CreateShopResponse> createShop(
            @RequestBody @Valid CreateShopRequest createShopRequest,
            @RequestAttribute("userId") Long userId) {

        CreateShopResponse createShopResponse = shopService.createShop(createShopRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createShopResponse);
    }

    @GetMapping("/shops")
    public ResponseEntity<CursorPageResponse<ShopSummaryResponse>> readByFilter(
            @RequestParam(required = false, name = "area") String area,
            @RequestParam(required = false, name = "tagIdList") List<Long> tagList,
            @RequestParam(required = true, name = "lastCursor") Long lastCursor,
            @RequestParam(required = false, name = "date")LocalDate date,
            @RequestParam(required = false, name = "time")LocalTime time
            ) {
        CursorPageResponse<ShopSummaryResponse> shopResponsePage = shopService.readByFilter(lastCursor, area, date, time, tagList);
        return ResponseEntity.ok(shopResponsePage);
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShopDetailResponse> readShopDetail(@PathVariable("shopId") Long shopId) {

        ShopDetailResponse shopDetailResponse = shopService.readShopDetail(shopId);
        return ResponseEntity.ok(shopDetailResponse);
    }

    @PatchMapping("/shops/{shopId}")
    public ResponseEntity<ShopDetailResponse> updateShop(
            @RequestAttribute("userId") Long userId,
            @PathVariable("shopId") Long shopId,
            @RequestBody @Valid UpdateShopRequest updateShopRequest) {

        ShopDetailResponse shopDetailResponse = shopService.updateShop(userId, shopId, updateShopRequest);
        return ResponseEntity.ok(shopDetailResponse);
    }

    @DeleteMapping("/shops/{shopId}")
    public ResponseEntity<Void> deleteShop(
            @RequestAttribute("userId") Long userId,
            @PathVariable("shopId") Long shopId) {

        shopService.deleteShop(userId, shopId);
        return ResponseEntity.ok().build();
    }
}
