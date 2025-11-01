package hairSalonReservation.sideProject.domain.shop.entity;

import jakarta.validation.constraints.Size;

public record AddressSearchRequest(@Size(max = 5) String cd) {
}
