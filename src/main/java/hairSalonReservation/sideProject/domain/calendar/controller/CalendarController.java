package hairSalonReservation.sideProject.domain.calendar.controller;

import hairSalonReservation.sideProject.domain.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
}
