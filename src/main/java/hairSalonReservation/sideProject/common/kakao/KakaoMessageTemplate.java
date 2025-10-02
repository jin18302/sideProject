package hairSalonReservation.sideProject.common.kakao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KakaoMessageTemplate {

    RESERVATION_REMINDER_TEMPLATE("124815");

    private final String template_id;
}
