package hairSalonReservation.sideProject.domain.review.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdateReviewRequest(
        @NotEmpty String title,
        @NotEmpty String content,
        Integer rating
) {
}
