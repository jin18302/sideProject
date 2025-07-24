package hairSalonReservation.sideProject.domain.serviceMenu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateServiceMenuRequest(
        @NotBlank @Size(max = 20) String name,
        @NotNull Integer price,
        @NotEmpty String introduction
) {
}
