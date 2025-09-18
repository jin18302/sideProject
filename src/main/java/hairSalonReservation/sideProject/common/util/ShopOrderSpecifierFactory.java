package hairSalonReservation.sideProject.common.util;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.common.cursor.ShopSortField;
import hairSalonReservation.sideProject.domain.shop.entity.QShop;

public class ShopOrderSpecifierFactory implements OrderSpecifierFactory<QShop, ShopSortField>{
    @Override
    public OrderSpecifier<?> generateOrderSpecifier(QShop shop, ShopSortField shopSortField, Order order) {

        Expression expression;

        switch (shopSortField) {
            case CREATED_AT -> expression = shop.createdAt;
            case LIKE_COUNT -> expression = shop.likeCount;
            default -> throw new RuntimeException();
        }

        return new OrderSpecifier<>(order, expression);
    }
    }
}
