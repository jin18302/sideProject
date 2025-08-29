package hairSalonReservation.sideProject.domain.review.reposiory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.common.cursor.CreatedAtStrategy;
import hairSalonReservation.sideProject.common.cursor.CursorStrategy;
import hairSalonReservation.sideProject.common.cursor.RatingStrategy;
import hairSalonReservation.sideProject.common.enums.SortDirection;
import hairSalonReservation.sideProject.common.enums.SortField;
import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hairSalonReservation.sideProject.domain.designer.entity.QDesigner.designer;
import static hairSalonReservation.sideProject.domain.reservation.entity.QReservation.reservation;
import static hairSalonReservation.sideProject.domain.review.entity.QReview.review;


@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewCustomRepository {

    @Value("${query.limit}")
    private int limit;

    private final JPAQueryFactory jpaQueryFactory;
    private CursorStrategy<QReview> cursorStrategy;

    @Override
    public List<ReviewResponse> findByShop(Long shopId, String cursor, SortField sortField) {

        setCursorStrategy(sortField);

        return jpaQueryFactory
                .select(Projections.constructor(
                        ReviewResponse.class,
                        review.id,
                        review.user.id,
                        review.reservation.id,
                        review.title,
                        review.content,
                        review.rating,
                        review.createdAt,
                        review.updatedAt
                ))
                .from(review)
                .join(review.reservation, reservation)
                .join(reservation.designer, designer)
                .where(
                        designer.shop.id.eq(shopId),
                        cursorStrategy.buildCursorPredicate(review, cursor)
                )
                .orderBy(getSortType(sortField, SortDirection.DESC))//TODO
                .limit(limit + 1)
                .fetch();
    }

    @Override
    public List<ReviewResponse> findByDesigner(Long designerId, Long cursor, SortField sortField) {

        BooleanBuilder booleanBuilder = new BooleanBuilder().and(reservation.designer.id.eq(designerId));

        return jpaQueryFactory
                .select(Projections.constructor(
                        ReviewResponse.class,
                        review.id,
                        review.user.id,
                        review.reservation.id,
                        review.title,
                        review.content,
                        review.rating,
                        review.createdAt,
                        review.updatedAt
                ))
                .from(review)
                .join(review.reservation, reservation)
                .where(booleanBuilder)
                .orderBy(review.id.desc())
                .limit(limit + 1)
                .fetch();
    }

    private void setCursorStrategy(SortField sortField) {
        switch (sortField) {
            case CREATED_AT -> this.cursorStrategy = new CreatedAtStrategy();
            case RATING -> this.cursorStrategy = new RatingStrategy();
        }
    }

    private OrderSpecifier<?>[] getSortType(SortField sortField, SortDirection sortDirection) {
        return null;
    }
}
