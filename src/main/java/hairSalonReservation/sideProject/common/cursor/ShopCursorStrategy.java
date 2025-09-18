package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import hairSalonReservation.sideProject.domain.shop.entity.QShop;
import org.springframework.stereotype.Component;

@Component
public class ShopCursorStrategy implements CursorStrategy<QShop, ShopSortField>{

    @Override
    public Predicate buildCursorPredicate(QShop shop, String cursor, Order order, ShopSortField sortField) {

        if(cursor == null){return Expressions.TRUE.isTrue(); }

        StringExpression cursorExpr;

        switch (sortField){
            case CREATED_AT -> cursorExpr = generateCreatedExpression(shop);
            case LIKE_COUNT -> cursorExpr = generatedLikeExpression(shop);
            default -> throw new RuntimeException();
        }

        switch (order){
            case DESC -> {return cursorExpr.gt(cursor);}
            case ASC -> {return cursorExpr.lt(cursor);}
            default -> throw new RuntimeException();
        }
    }

    public StringExpression generateCreatedExpression(QShop shop) {

                return Expressions.stringTemplate(
                        "CONCAT(DATE_FORMAT({0}, '%Y%m%d%H%i%s%f'), LPAD({1}, 20, '0'))",
                        shop.createdAt, String.valueOf(shop.id)
                );
    }

    public StringExpression generatedLikeExpression(QShop shop) {

              return  Expressions.stringTemplate(
                        "CONCAT(DATE_FORMAT({0}, '%Y%m%d%H%i%s%f'), LPAD({1}, 20, '0'))",
                        shop.createdAt, String.valueOf(shop.id)
                );
    }
}
