package hairSalonReservation.sideProject.domain.review.reposiory;

import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewCustomRepository {

    List<ReviewResponse> findByShop(Long shopId, Long cursor);

    List<ReviewResponse> findByDesigner(Long designerId, Long cursor);
}
