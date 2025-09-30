package hairSalonReservation.sideProject.domain.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.reservation.entity.ScheduleBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
}
