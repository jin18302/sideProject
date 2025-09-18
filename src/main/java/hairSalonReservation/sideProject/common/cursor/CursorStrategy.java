package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;

public interface CursorStrategy<T, F extends SortableField> {

    Predicate buildCursorPredicate(T t, String cursor, Order order, F f);
}
