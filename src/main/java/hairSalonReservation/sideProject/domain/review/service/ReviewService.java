package hairSalonReservation.sideProject.domain.review.service;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.common.dto.CursorPageResponse;
import hairSalonReservation.sideProject.common.exception.BadRequestException;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import hairSalonReservation.sideProject.common.exception.NotFoundException;
import hairSalonReservation.sideProject.common.util.ReviewFactory;
import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.entity.ReservationStatus;
import hairSalonReservation.sideProject.domain.reservation.repository.ReservationRepository;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortType;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import hairSalonReservation.sideProject.domain.review.dto.request.CreateReviewRequest;
import hairSalonReservation.sideProject.domain.review.dto.request.UpdateReviewRequest;
import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.domain.review.entity.Review;
import hairSalonReservation.sideProject.domain.review.reposiory.ReviewRepository;
import hairSalonReservation.sideProject.domain.review.reposiory.ReviewRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    @Value("${query.limit}")
    private int limit;

    private final ReviewRepository reviewRepository;
    private final ReviewRepositoryCustomImpl reviewRepositoryCustom;
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

    public CursorPageResponse<ReviewResponse> readByShop(Long shopId, String cursor, String sort, String order){

        ReviewSortType sortType = ReviewSortType.valueOf(sort);
        Order orderBy = Order.valueOf(order);

        List<ReviewResponse> reviewResponseList = reviewRepositoryCustom.findByShop(shopId, cursor, sortType, orderBy);

        boolean isLast = reviewResponseList.size() < limit + 1;
        if(!isLast){reviewResponseList.remove(limit);}

        Long lastCursor = reviewResponseList.isEmpty() ? 0 : reviewResponseList.get(reviewResponseList.size() - 1).id();
        return new CursorPageResponse<>(reviewResponseList, lastCursor, isLast);
    }

//    public CursorPageResponse<ReviewResponse> readByDesigner(Long designerId, Long cursor, String sort){
//
//        SortField sortField = SortField.valueOf(sort);
//        List<ReviewResponse> reviewResponseList = reviewRepositoryCustom.findByDesigner(designerId, cursor, sortField);
//
//        boolean isLast = reviewResponseList.size() < limit + 1;
//        if(!isLast){reviewResponseList.remove(limit);}
//
//        Long lastCursor = reviewResponseList.isEmpty() ? 0 : reviewResponseList.get(reviewResponseList.size() - 1).id();
//        //TODO: 마지막 커서를 어떻게 보낼것인가...,
//
//        return new CursorPageResponse<>(reviewResponseList, lastCursor, isLast);
//    }

    @Transactional
    public ReviewResponse updateReview(Long reviewId, Long userId, UpdateReviewRequest request){

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        Long reviewWriterId = review.getId();
        if(!reviewWriterId.equals(userId)){ throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        review.update(request.title(), request.content(), request.rating());
        return ReviewResponse.from(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, Long userId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.REVIEW_NOT_FOUND));

        Long reviewWriterId = review.getId();
        if(!reviewWriterId.equals(userId)){ throw new ForbiddenException(ErrorCode.FORBIDDEN);}

        reviewRepository.deleteById(reviewId);
    }
}
