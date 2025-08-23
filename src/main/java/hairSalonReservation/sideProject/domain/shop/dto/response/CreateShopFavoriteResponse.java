package hairSalonReservation.sideProject.domain.shop.dto.response;

import hairSalonReservation.sideProject.domain.shop.entity.ShopFavorite;

public record CreateShopFavoriteResponse(Long id, Long userId) {

    public static CreateShopFavoriteResponse from(ShopFavorite shopFavorite){
        return new CreateShopFavoriteResponse(shopFavorite.getId(), shopFavorite.getUser().getId());
    }
}
