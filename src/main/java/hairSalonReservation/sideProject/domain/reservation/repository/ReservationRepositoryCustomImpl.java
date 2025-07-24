package hairSalonReservation.sideProject.domain.reservation.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

import static hairSalonReservation.sideProject.domain.reservation.entity.QReservation.reservation;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existByDesignerIdAndSlot(Long designerId, LocalDate date, LocalTime time) {

        Long count = queryFactory
                .select(reservation.count())
                .from(reservation)
                .where(
                        reservation.designer.id.eq(designerId),
                        reservation.date.eq(date),
                        reservation.time.eq(time)
                )
                .fetchOne();

        return count != 0;
    }
}
