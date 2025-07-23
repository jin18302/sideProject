package hairSalonReservation.sideProject.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                message = "비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상이어야 합니다."
        )
        String password
) {
}
