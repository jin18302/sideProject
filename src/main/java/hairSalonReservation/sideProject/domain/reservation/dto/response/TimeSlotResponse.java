package hairSalonReservation.sideProject.domain.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record TimeSlotResponse(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm") LocalTime time, boolean isReservable) {

    public static TimeSlotResponse of (LocalTime time, boolean isReservable){
        return new TimeSlotResponse(time, isReservable);
    }
}
