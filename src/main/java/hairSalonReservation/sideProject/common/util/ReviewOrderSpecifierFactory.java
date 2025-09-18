package hairSalonReservation.sideProject.common.util;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortField;
import org.springframework.stereotype.Component;

@Component
public class ReviewOrderSpecifierFactory implements OrderSpecifierFactory<QReview, ReviewSortField>{

    @Override
    public OrderSpecifier<?> generateOrderSpecifier(QReview qReview, ReviewSortField sortField, Order order) {
        Expression expression;

        switch (sortField) {
            case CREATED_AT -> expression = qReview.createdAt;
            case RATING -> expression = qReview.rating;
            default -> throw new RuntimeException();
        }

        return new OrderSpecifier<>(order, expression);
    }
}
