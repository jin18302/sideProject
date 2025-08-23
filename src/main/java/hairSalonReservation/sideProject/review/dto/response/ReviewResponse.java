package hairSalonReservation.sideProject.review.dto.response;

import hairSalonReservation.sideProject.review.entity.Review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        Long userId,
        Long reservationId,
        String title,
        String content,
        Integer rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ReviewResponse from(Review review){

        return new ReviewResponse(
                review.getId(),
                review.getUser().getId(),
                review.getReservation().getId(),
                review.getTitle(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
