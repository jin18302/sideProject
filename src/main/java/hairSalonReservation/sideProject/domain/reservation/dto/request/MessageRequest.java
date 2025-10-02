package hairSalonReservation.sideProject.domain.reservation.dto.request;

import hairSalonReservation.sideProject.common.kakao.KakaoMessageTemplate;
import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;

public record MessageRequest(String template_id, RequestArgDto template_args) {
    public static MessageRequest of(KakaoMessageTemplate template, Reservation reservation) {
        return new MessageRequest(template.getTemplate_id(), RequestArgDto.from(reservation));
    }
}




