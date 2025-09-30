package hairSalonReservation.sideProject.domain.reservation.dto.response;

import com.fasterxml.jackson.core.type.TypeReference;
import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.reservation.entity.ScheduleBlock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ScheduleBlockResponse(Long id, LocalDate date, List<LocalTime> time) {

    public static ScheduleBlockResponse from(ScheduleBlock block){
        List<LocalTime> timeList = JsonHelper.fromJsonToList(block.getTimeList(), new TypeReference<>(){});
        return new ScheduleBlockResponse(block.getId(), block.getDate(), timeList);
    }
}
