package hairSalonReservation.sideProject.common.util;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortType;

public interface OrderSpecifierFactory<T extends EntityPath<?>> {

     OrderSpecifier<?> temp(T t, ReviewSortType sortType, Order order);
}
