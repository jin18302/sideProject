package hairSalonReservation.sideProject.domain.serviceMenu.dto.request;

import hairSalonReservation.sideProject.domain.serviceMenu.entity.MenuCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateServiceMenuRequest(
        @NotBlank MenuCategory category,
        @NotBlank @Size(max = 20) String name,
        @NotNull Integer price,
        @NotEmpty String introduction
) {
}
