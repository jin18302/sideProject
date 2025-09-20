package hairSalonReservation.sideProject.domain.shop.service;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.common.cursor.OrderSpecifierFactory;
import hairSalonReservation.sideProject.domain.shop.entity.ShopSortField;
import hairSalonReservation.sideProject.domain.shop.entity.QShop;
import org.springframework.stereotype.Component;

@Component
public class ShopOrderSpecifierFactory implements OrderSpecifierFactory<QShop, ShopSortField> {
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
