package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import org.springframework.stereotype.Component;

@Component
public class CreatedAtStrategy implements CursorStrategy<QReview>{

    public Predicate buildCursorPredicate(QReview review, String lastCursor) {

        SortDirection sortDirection = SortDirection.DESC;

        StringExpression cursorExpr =
                Expressions.stringTemplate(
                        "CONCAT(DATE_FORMAT({0}, '%Y%m%d%H%i%s%f'), LPAD({1}, 20, '0'))"
                        ,review.createdAt, review.id
                );

        switch (sortDirection){
            case DESC -> {return cursorExpr.gt(lastCursor);}
            case ASC -> {return cursorExpr.lt(lastCursor);}
            default -> throw new RuntimeException();
        }
    }
}