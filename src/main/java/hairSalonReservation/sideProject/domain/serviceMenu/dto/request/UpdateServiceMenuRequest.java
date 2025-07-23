package hairSalonReservation.sideProject.domain.serviceMenu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateServiceMenuRequest(
        @NotBlank String name,
        @NotNull Integer price,
        @NotEmpty String introduction
) {
}
