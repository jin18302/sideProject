package hairSalonReservation.sideProject.domain.reservation.repository;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.reservation.dto.response.ReadClosedDaysResponse;
import hairSalonReservation.sideProject.domain.reservation.entity.ScheduleBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static hairSalonReservation.sideProject.domain.designer.entity.QDesigner.designer;
import static hairSalonReservation.sideProject.domain.reservation.entity.QScheduleBlock.scheduleBlock;

@Repository
@RequiredArgsConstructor
public class ScheduleBlockRepositoryCustomImpl implements ScheduleBlockRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ScheduleBlock> findByDesignerIdAndDate(Long designerId, LocalDate date) {

        return Optional.ofNullable(
                queryFactory.select(scheduleBlock)
                        .from(scheduleBlock)
                        .join(scheduleBlock.designer, designer).fetchJoin()
                        .where(scheduleBlock.date.eq(date))
                        .fetchOne());
    }

    @Override
    public List<ScheduleBlock> findByDesignerIdAndMonth(Long designerId, Integer month) {
        return
                queryFactory.select(scheduleBlock)
                        .from(scheduleBlock)
                        .join(scheduleBlock.designer, designer).fetchJoin()
                        .where(
                                scheduleBlock.date.month().eq(month),
                                scheduleBlock.isDayOff.isTrue()
                        )
                        .fetch();
    }

}
