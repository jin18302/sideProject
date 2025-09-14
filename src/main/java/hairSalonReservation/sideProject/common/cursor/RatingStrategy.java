package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import org.springframework.stereotype.Component;

import static com.querydsl.core.types.dsl.StringExpressions.lpad;

@Component
public class RatingStrategy implements CursorStrategy<QReview>{
    @Override
    public Predicate buildCursorPredicate(QReview review, String lastCursor) {

        SortDirection sortDirection = SortDirection.DESC;

        StringExpression cursorExpr =
                Expressions.stringTemplate(
                        "CONCAT({0}, LPAD({1}, 20, '0'))"
                        ,review.rating, review.id
                );

        switch (sortDirection){
            case DESC -> {return cursorExpr.gt(lastCursor);}
            case ASC -> {return cursorExpr.lt(lastCursor);}
            default -> throw new RuntimeException();
        }
    }
}
