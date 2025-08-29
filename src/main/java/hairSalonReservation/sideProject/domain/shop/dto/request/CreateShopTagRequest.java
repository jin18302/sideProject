package hairSalonReservation.sideProject.domain.shop.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateShopTagRequest(@NotEmpty  String name) {
}
