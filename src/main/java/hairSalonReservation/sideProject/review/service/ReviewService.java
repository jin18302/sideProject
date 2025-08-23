package hairSalonReservation.sideProject.review.service;

import hairSalonReservation.sideProject.common.exception.BadRequestException;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import hairSalonReservation.sideProject.common.exception.NotFoundException;
import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;
import hairSalonReservation.sideProject.domain.reservation.repository.ReservationRepository;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import hairSalonReservation.sideProject.review.dto.request.CreateReviewRequest;
import hairSalonReservation.sideProject.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.review.entity.Review;
import hairSalonReservation.sideProject.review.reposiory.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponse createReview(Long reservationId, Long userId, CreateReviewRequest request){

        if(!userRepository.existsById(userId)){throw new NotFoundException(ErrorCode.USER_NOT_FOUND);}
        User user = userRepository.getReferenceById(userId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESERVATION_NOT_FOUND));

        Long reserverId = reservation.getUser().getId();

        if(!reserverId.equals(userId)){throw new ForbiddenException(ErrorCode.FORBIDDEN);}
        if(reservation.getReservationStatus() != ReservationStatus.VISITED){
            throw new BadRequestException(ErrorCode.REVIEW_NOT_ALLOWED_BEFORE_VISIT);
        }

        Review review = Review.of(
                user,
                reservation,
                request.title(),
                request.content(),
                request.rating()
        );

        reviewRepository.save(review);
        return ReviewResponse.from(review);
    }
}
