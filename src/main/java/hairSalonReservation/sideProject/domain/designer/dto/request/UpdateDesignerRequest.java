package hairSalonReservation.sideProject.domain.designer.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateDesignerRequest(
        @NotEmpty @Size(max = 20) String name,
        @NotEmpty String introduction,
        @NotEmpty String profileImage,
        List<String> imageUriList,
        List<String> snsUriList
) {
}
