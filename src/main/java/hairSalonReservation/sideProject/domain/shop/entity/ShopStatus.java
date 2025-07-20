package hairSalonReservation.sideProject.domain.shop.entity;

public enum ShopStatus {
    NOT_OPENED,
    OPEN,             // 영업 중
    CLOSED,     // 오늘 영업 종료
    BEFORE_OPEN,      // 오늘 아직 시작 전
    TEMP_CLOSED,      // 임시 휴업
    SHUTDOWN,         // 폐업
}

