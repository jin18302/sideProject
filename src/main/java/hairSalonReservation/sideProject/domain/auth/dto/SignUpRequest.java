package hairSalonReservation.sideProject.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotEmpty
        @Size(max = 20)
        String name,

        @NotEmpty
        @Email
        String email,

        @NotEmpty
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                message = "비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상이어야 합니다."
        )
        String password,

        @NotEmpty
        String gender,

        @NotEmpty
        String userRole
) {
}
