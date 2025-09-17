package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;
import hairSalonReservation.sideProject.domain.review.entity.SortField;

public interface CursorStrategy<T> {

    Predicate buildCursorPredicate(T t, String cursor, Order order, SortField sortField);
}
