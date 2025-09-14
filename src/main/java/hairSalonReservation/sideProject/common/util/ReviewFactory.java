package hairSalonReservation.sideProject.common.util;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortType;
import org.springframework.stereotype.Component;

@Component
public class ReviewFactory<T> implements OrderSpecifierFactory<QReview>{

    @Override
    public OrderSpecifier<?> temp(QReview qReview, ReviewSortType sortType, Order order) {

        Expression expression;

        switch (sortType){
            case CREATED_AT -> expression = qReview.createdAt;
            case RATING -> expression = qReview.rating;
            default -> throw new RuntimeException();
        }

        return new OrderSpecifier<>(order, expression);
    }

    public static OrderSpecifier<?> temp2(T t, ReviewSortType sortType, Order order) {

        Expression expression;

        switch (sortType){
            case CREATED_AT -> expression = t.createdAt;
            case RATING -> expression = qReview.rating;// 이것들이 런타임에 정해지잖슴.
            default -> throw new RuntimeException();
        }

        return new OrderSpecifier<>(order, expression);
    }
}
