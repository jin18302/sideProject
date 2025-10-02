package hairSalonReservation.sideProject.domain.reservation.service;

import hairSalonReservation.sideProject.common.cursor.CursorPageResponse;
import hairSalonReservation.sideProject.common.kakao.KakaoClient;
import hairSalonReservation.sideProject.common.kakao.KakaoMessageTemplate;
import hairSalonReservation.sideProject.domain.reservation.dto.request.MessageRequest;
import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.repository.ReservationRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final ReservationRepositoryCustomImpl reservationRepositoryCustom;
    private final KakaoClient kakaoClient;

    //스케줄러가 딱 정각 아니면, n시 30분에만 동작해야함
    @Scheduled(cron = "0 0,30 * * * *", zone = "Asia/Seoul")
    public void process(){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now()
                .truncatedTo(ChronoUnit.MINUTES)
                .minusHours(1);

        boolean isLast ;
        Long cursor = 0L;

        do{
            List<Reservation> response = reservationRepositoryCustom.findByReservationSloat(date, time, cursor);
            CursorPageResponse<Reservation> cursorPageResponse = CursorPageResponse.of(response, Reservation::getId);

            isLast = cursorPageResponse.isLastPage();
            cursor = cursorPageResponse.lastCursor();

            cursorPageResponse.content().stream()
                    .map(e -> MessageRequest.of(KakaoMessageTemplate.RESERVATION_REMINDER_TEMPLATE, e))
                    .forEach(kakaoClient::sendMessage);

        }while (isLast);
    }
}
