package hairSalonReservation.sideProject.domain.shop.dto.response;

import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import hairSalonReservation.sideProject.domain.shop.entity.ShopStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShopDetailResponse(
        Long id,
        String name,
        String address,
        String phoneNumber,
        LocalTime openTime,
        LocalTime endTime,
        String introduction,
        String imageUrlList,
        String snsUriList,
        ShopStatus shopStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {
    public static ShopDetailResponse from(Shop shop) {
        return new ShopDetailResponse(
                shop.getId(),
                shop.getName(),
                shop.getAddress(),
                shop.getPhoneNumber(),
                shop.getOpenTime(),
                shop.getEndTime(),
                shop.getIntroduction(),
                shop.getImageUrlList(),
                shop.getSnsUriList(),
                shop.getShopStatus(),
                shop.getCreatedAt(),
                shop.getUpdatedAt(),
                shop.getDeletedAt()
        );
    }
}
