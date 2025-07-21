package hairSalonReservation.sideProject.domain.shop.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.List;

public record CreateShopRequest(
        @Size(max = 15) @NotEmpty String name,
        @Size(max = 30) @NotEmpty String businessId,
        @NotEmpty String address,
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$") @NotEmpty String phoneNumber,
        @JsonFormat(pattern = "HH:mm") @NotNull LocalTime openTime,
        @JsonFormat(pattern = "HH:mm") @NotNull LocalTime endTime,
        @NotEmpty String introduction,
        List<String> imageUrlList,
        List<String> snsUriList
) {
}
