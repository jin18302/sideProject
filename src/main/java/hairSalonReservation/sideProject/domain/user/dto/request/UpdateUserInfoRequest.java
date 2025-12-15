package hairSalonReservation.sideProject.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserInfoRequest(
        @NotEmpty
        @Size(max = 15)
        String name,

        @NotEmpty
        @Email
        String email
) {
}
