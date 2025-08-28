package hairSalonReservation.sideProject.domain.review.reposiory;

import hairSalonReservation.sideProject.common.enums.SortType;
import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewCustomRepository {

    List<ReviewResponse> findByShop(Long shopId, String cursor, SortType sortType);

    List<ReviewResponse> findByDesigner(Long designerId, Long cursor, SortType sortType);
}
