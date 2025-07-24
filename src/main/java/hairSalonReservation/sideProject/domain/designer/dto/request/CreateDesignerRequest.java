package hairSalonReservation.sideProject.domain.designer.dto.request;

import hairSalonReservation.sideProject.domain.designer.entity.DayOffWeekday;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateDesignerRequest(
        @NotEmpty @Size(max = 20) String name,
        @NotEmpty String introduction,
        @NotEmpty String profileImage,
        List<DayOffWeekday> dayOffWeekdayList,
        List<String> imageUriList,
        List<String> snsUriList
) {
}
