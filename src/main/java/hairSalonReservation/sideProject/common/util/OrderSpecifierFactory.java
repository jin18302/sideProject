package hairSalonReservation.sideProject.common.util;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.common.cursor.SortableField;

public interface OrderSpecifierFactory<T extends EntityPath<?> , F extends SortableField> {

     OrderSpecifier<?> generateOrderSpecifier(T t, F f, Order order);
}
