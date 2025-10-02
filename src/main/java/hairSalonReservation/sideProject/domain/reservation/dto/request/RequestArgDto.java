package hairSalonReservation.sideProject.domain.reservation.dto.request;

import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RequestArgDto(//TODO
        String customerName,
        String shopName,
        String reservationTime,
        String designerName,
        String serviceName
) {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm");

    public RequestArgDto(
            String customerName,
            String shopName,
            LocalDateTime reservationTime,
            String designerName,
            String serviceName
    ) {

        this(
                customerName,
                shopName,
                reservationTime != null ? reservationTime.format(formatter) : null,
                designerName,
                serviceName
        );
    }

    public static RequestArgDto from(Reservation reservation) {
        return new RequestArgDto(
                reservation.getUser().getName(),
                reservation.getDesigner().getShop().getName(),
                LocalDateTime.of(reservation.getDate(), reservation.getTime()),
                reservation.getDesigner().getName(),
                reservation.getServiceMenu().getName()
        );
    }

}
