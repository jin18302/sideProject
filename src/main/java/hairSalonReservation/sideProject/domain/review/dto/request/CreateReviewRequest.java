package hairSalonReservation.sideProject.domain.review.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateReviewRequest(
        @NotEmpty String title,
        @NotEmpty String content,
        Integer rating
) {
}
