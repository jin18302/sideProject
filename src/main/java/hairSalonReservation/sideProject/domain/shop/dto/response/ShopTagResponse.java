package hairSalonReservation.sideProject.domain.shop.dto.response;

import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;

public record ShopTagResponse(String name) {

    public static ShopTagResponse from(ShopTag shopTag){
        return new ShopTagResponse(shopTag.getName());
    }
}
