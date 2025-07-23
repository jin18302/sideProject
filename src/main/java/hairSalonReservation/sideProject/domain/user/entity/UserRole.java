package hairSalonReservation.sideProject.domain.user.entity;

import java.util.Arrays;

public enum UserRole {
    CUSTOMER,     // 일반 사용자
    OWNER,        // 사업주 (사장님)
//    DESIGNER,     // 디자이너
    ADMIN ;        // 관리자 (전체 시스템 제어 권한)

    public static UserRole of(String userRole) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(userRole))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 userRole 입니다."));
    }
}
