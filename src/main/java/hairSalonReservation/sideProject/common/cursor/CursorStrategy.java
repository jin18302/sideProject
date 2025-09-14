package hairSalonReservation.sideProject.common.cursor;

import com.querydsl.core.types.Predicate;

public interface CursorStrategy<T> {

    Predicate buildCursorPredicate(T t, String cursor);
}
