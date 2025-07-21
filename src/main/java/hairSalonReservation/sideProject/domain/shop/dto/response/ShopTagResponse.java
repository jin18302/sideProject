package hairSalonReservation.sideProject.domain.shop.dto.response;

import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;

public record ShopTagResponse(Long id, String name) {

    public static ShopTagResponse from(ShopTag shopTag){
        return new ShopTagResponse(shopTag.getId(), shopTag.getName());
    }
}
