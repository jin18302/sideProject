package hairSalonReservation.sideProject.domain.review.reposiory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.common.cursor.CursorStrategy;
import hairSalonReservation.sideProject.common.util.OrderSpecifierFactory;
import hairSalonReservation.sideProject.domain.review.dto.response.ReviewResponse;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import hairSalonReservation.sideProject.domain.review.entity.ReviewSortField;
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
    private final OrderSpecifierFactory<QReview, ReviewSortField> orderSpecifierFactory;
    private final CursorStrategy<QReview, ReviewSortField> cursorStrategy;

    @Override
    public List<ReviewResponse> findByShop(Long shopId, String cursor, ReviewSortField sortType, Order order) {

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
                        cursorStrategy.buildCursorPredicate(review, cursor, order, sortType)
                )
                .orderBy(orderSpecifierFactory.generateOrderSpecifier(review, sortType, order))
                .limit(limit + 1)
                .fetch();
    }

    @Override
    public List<ReviewResponse> findByDesigner(Long designerId, String cursor, ReviewSortField sortField, Order order) {

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
                .where(booleanBuilder,
                        cursorStrategy.buildCursorPredicate(review, cursor, Order.ASC, sortField))
                .orderBy(review.id.desc())
                .limit(limit + 1)
                .fetch();
    }
}
