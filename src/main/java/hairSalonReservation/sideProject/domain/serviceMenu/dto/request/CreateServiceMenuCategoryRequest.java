package hairSalonReservation.sideProject.domain.serviceMenu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateServiceMenuCategoryRequest(@NotBlank @Size(max = 15) String name) {
}
