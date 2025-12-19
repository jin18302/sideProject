package hairSalonReservation.sideProject.domain.reservation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TimeSlotCreator {

    @Transactional
    public List<LocalTime> createTimeSlot(LocalTime startTime, LocalTime endTime) {

        List<LocalTime> timeSlotList = new ArrayList<>();

        for (LocalTime i = startTime; i.isBefore(endTime); i = i.plusMinutes(30)) {
            timeSlotList.add(i);
        }

        timeSlotList.add(endTime);
        return timeSlotList;
    }
}
