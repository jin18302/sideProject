package hairSalonReservation.sideProject.domain.user.entity;

import java.util.Arrays;

public enum Gender {
    MALE,   // 남성
    FEMALE;  // 여성

    public static Gender of(String gender) {
        return Arrays.stream(Gender.values())
                .filter(r -> r.name().equalsIgnoreCase(gender))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Gender 입니다."));
    }
}
