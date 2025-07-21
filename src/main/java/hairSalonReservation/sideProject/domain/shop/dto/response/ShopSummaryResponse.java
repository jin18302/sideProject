package hairSalonReservation.sideProject.domain.shop.dto.response;

import hairSalonReservation.sideProject.domain.shop.entity.ShopStatus;

import java.util.List;

public record ShopSummaryResponse(
        Long id,
        String name,
        String introduction,
        ShopStatus shopStatus,
        List<String> imageList
 ) {
}
