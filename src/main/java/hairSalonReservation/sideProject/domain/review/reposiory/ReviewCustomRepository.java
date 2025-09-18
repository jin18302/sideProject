package hairSalonReservation.sideProject.domain.review.reposiory;

import com.querydsl.core.types.Order;
import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortField;

import java.util.List;

public interface ReviewCustomRepository {

    List<ReviewResponse> findByShop(Long shopId, String cursor, ReviewSortField sortType, Order order);

    List<ReviewResponse> findByDesigner(Long designerId, String cursor, ReviewSortField sortField, Order order);
}
