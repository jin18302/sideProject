package hairSalonReservation.sideProject.domain.reservation.entity;

public enum ReservationStatus {
    REQUESTED,   // 예약 신청 (사용자 측 예약 완료 상태)
    CONFIRMED,   // 예약 확정 (디자이너/사장님이 승인)
    CANCELLED,   // 예약 취소 (사용자 or 관리자)
    VISITED,     // 방문 완료
    NO_SHOW       // 노쇼 (예약했지만 방문하지 않음)
}
