package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortField;
import org.springframework.stereotype.Component;

@Component
public class ReviewCursorStrategy implements CursorStrategy<QReview, ReviewSortField>{

    @Override
    public Predicate buildCursorPredicate(QReview review, String lastCursor, Order order, ReviewSortField sortField) {

        if(lastCursor == null){return Expressions.TRUE.isTrue(); }

        StringExpression cursorExpr;

        switch (sortField){
            case CREATED_AT -> cursorExpr = createdAtExpression(review);
            case RATING -> cursorExpr = ratingAtExpression(review);
            default -> throw new RuntimeException();
        }

        switch (order){
            case DESC -> {return cursorExpr.gt(lastCursor);}
            case ASC -> {return cursorExpr.lt(lastCursor);}
            default -> throw new RuntimeException();
        }
    }

    private StringExpression createdAtExpression(QReview review){
        return  Expressions.stringTemplate(
                        "CONCAT(DATE_FORMAT({0}, '%Y%m%d%H%i%s%f'), LPAD({1}, 20, '0'))",
                        review.createdAt, String.valueOf(review.id));
    }

    private StringExpression ratingAtExpression (QReview review) {
        return Expressions.stringTemplate(
                        "CONCAT({0}, LPAD({1}, 20, '0'))"
                        ,review.rating, review.id);
    }
}