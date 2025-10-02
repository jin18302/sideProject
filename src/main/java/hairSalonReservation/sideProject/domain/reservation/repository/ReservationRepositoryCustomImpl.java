package hairSalonReservation.sideProject.domain.reservation.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.reservation.dto.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static hairSalonReservation.sideProject.domain.reservation.entity.QReservation.reservation;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {

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

    @Override
    public List<ReservationResponse> findByDesignerIdAndDate(Long designerId, LocalDate date) {

        return queryFactory
                .select(Projections.constructor(
                        ReservationResponse.class,
                        reservation.id,
                        reservation.serviceMenu.id,
                        reservation.designer.id,
                        reservation.user.id,
                        reservation.reservationStatus
                ))
                .from(reservation)
                .where(
                        reservation.designer.id.eq(designerId),
                        reservation.date.eq(Objects.requireNonNullElseGet(date, LocalDate::now))
                )
                .fetch();
    }

    @Override
    public List<Reservation> findByReservationSloat(LocalDate date, LocalTime time, Long cursor) {

       return queryFactory
                .select(reservation)
                .from(reservation)
                .join(reservation.user, user).fetchJoin()
                .join(reservation.designer, designer).fetchJoin()
                .join(designer.shop, shop).fetchJoin()
                .where(
                        reservation.date.eq(date),
                        reservation.time.eq(time),
                        reservation.id.gt(cursor)
                )
                .orderBy(reservation.id.asc())
                .limit(queryProperties.getLimit())
                .fetch();
    }
}
