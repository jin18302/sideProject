package hairSalonReservation.sideProject.domain.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CreateScheduleBlockRequest(@JsonFormat(pattern = "yyyy-MM-dd") @NotNull LocalDate date,
                                         @JsonFormat(pattern = "HH:mm") @NotNull List<LocalTime> time) {
}
