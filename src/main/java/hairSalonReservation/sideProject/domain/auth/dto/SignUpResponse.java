package hairSalonReservation.sideProject.domain.auth.dto;

import hairSalonReservation.sideProject.domain.user.entity.Gender;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.entity.UserRole;

import java.time.LocalDateTime;

public record SignUpResponse(
        Long id,
        String name,
        String email,
        Gender gender,
        UserRole userRole,
        LocalDateTime createdAt
) {

    public static SignUpResponse from(User user){
        return new SignUpResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getUserRole(),
                user.getCreatedAt()
        );
    }
}
