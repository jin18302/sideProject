package hairSalonReservation.sideProject.common.util;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.domain.review.entity.SortField;

public interface OrderSpecifierFactory<T extends EntityPath<?>> {

     OrderSpecifier<?> generateOrderSpecifier(T t, SortField sortType, Order order);
}
