package hairSalonReservation.sideProject.review.reposiory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.review.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hairSalonReservation.sideProject.domain.designer.entity.QDesigner.designer;
import static hairSalonReservation.sideProject.domain.reservation.entity.QReservation.reservation;
import static hairSalonReservation.sideProject.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewCustomRepository {

    @Value("${query.limit}")
    private int limit;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewResponse> findByShop(Long shopId, Long cursor) {

        BooleanBuilder booleanBuilder = new BooleanBuilder().and(designer.shop.id.eq(shopId));
        if(cursor != null){booleanBuilder.and(review.id.lt(cursor));}

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
                .where(booleanBuilder)
                .orderBy(review.id.desc())
                .limit(limit + 1)
                .fetch();
    }

    @Override
    public List<ReviewResponse> findByDesigner(Long designerId, Long cursor) {

        BooleanBuilder booleanBuilder = new BooleanBuilder().and(reservation.designer.id.eq(designerId));
        if(cursor != null){booleanBuilder.and(review.id.lt(cursor));}

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
}
