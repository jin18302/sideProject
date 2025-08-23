package hairSalonReservation.sideProject.review.reposiory;

import hairSalonReservation.sideProject.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.review.entity.Review;

import java.util.List;

public interface ReviewCustomRepository {

    List<ReviewResponse> findByShop(Long shopId, Long cursor);

    List<ReviewResponse> findByDesigner(Long designerId, Long cursor);
}
