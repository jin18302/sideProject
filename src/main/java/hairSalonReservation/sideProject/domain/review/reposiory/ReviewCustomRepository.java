package hairSalonReservation.sideProject.domain.review.reposiory;

import com.querydsl.core.types.Order;
import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortType;

import java.util.List;

public interface ReviewCustomRepository {

    List<ReviewResponse> findByShop(Long shopId, String cursor, ReviewSortType sortType, Order order);

    List<ReviewResponse> findByDesigner(Long designerId, Long cursor, SortField sortField);
}
