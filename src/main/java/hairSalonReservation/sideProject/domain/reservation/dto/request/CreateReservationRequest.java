package hairSalonReservation.sideProject.domain.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationRequest(
        Long serviceMenuId,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull LocalDate date,
        @JsonFormat(pattern = "HH:mm") @NotNull LocalTime time
) {
}
