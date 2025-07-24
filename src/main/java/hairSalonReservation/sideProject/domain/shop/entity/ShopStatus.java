package hairSalonReservation.sideProject.domain.shop.entity;

import java.util.Arrays;

public enum ShopStatus {
    NOT_OPENED,
    OPEN,             // 영업 중
    CLOSED,     // 오늘 영업 종료
    BEFORE_OPEN,      // 오늘 아직 시작 전
    TEMP_CLOSED,      // 임시 휴업
    SHUTDOWN;      // 폐업

    public static ShopStatus of(String shopStatus) {
        return Arrays.stream(ShopStatus.values())
                .filter(r -> r.name().equalsIgnoreCase(shopStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 ShopStatus 입니다."));
    }
}

