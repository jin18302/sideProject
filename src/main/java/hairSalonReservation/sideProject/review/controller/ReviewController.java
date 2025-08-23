package hairSalonReservation.sideProject.review.controller;

import hairSalonReservation.sideProject.common.dto.CursorPageResponse;
import hairSalonReservation.sideProject.review.dto.request.CreateReviewRequest;
import hairSalonReservation.sideProject.review.dto.request.UpdateReviewRequest;
import hairSalonReservation.sideProject.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("reservations/{reservationId}")
    public ResponseEntity<ReviewResponse> createReview(
            @PathVariable(name = "reservationId") Long reservationId,
            @RequestAttribute("userId") Long userId,
            @RequestBody CreateReviewRequest createReviewRequest
    ){

        ReviewResponse reviewResponse = reviewService.createReview(reservationId, userId, createReviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }

    @GetMapping("/shops/{shopId}/reviews")
    public ResponseEntity<CursorPageResponse<ReviewResponse>> readByShop(@PathVariable(name = "shopId")Long shopId,
                                                                         @RequestParam(name = "cursor", required = false) Long cursor
    ){

        CursorPageResponse<ReviewResponse> reviewResponseList = reviewService.readByShop(shopId,cursor);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseList);
    }

    @GetMapping("/designers/{designerId}/reviews")
    public ResponseEntity<CursorPageResponse<ReviewResponse>> readByDesigner(@PathVariable(name = "designerId")Long designerId,
                                                               @RequestParam(name = "cursor", required = false) Long cursor
    ){

        CursorPageResponse<ReviewResponse> reviewResponseList = reviewService.readByDesigner(designerId, cursor);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseList);
    }

    @PatchMapping("reservations/{reservationId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable(name = "reservationId") Long reservationId,
            @RequestAttribute("userId") Long userId,
            @RequestBody UpdateReviewRequest updateReviewRequest
    ){
        ReviewResponse reviewResponse = reviewService.updateReview(reservationId, userId, updateReviewRequest);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponse);
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable(name = "reviewId") Long reviewId,
            @RequestAttribute("userId") Long userId
    ){
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
