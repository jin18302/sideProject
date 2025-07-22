package hairSalonReservation.sideProject.domain.designer.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.designer.dto.response.DesignerSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hairSalonReservation.sideProject.domain.designer.entity.QDesigner.designer;

@Repository
@RequiredArgsConstructor
public class DesignerRepositoryCustomImpl implements DesignerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<DesignerSummaryResponse> findByShopId(Long shopId) {

        return queryFactory.select(Projections.constructor(
                        DesignerSummaryResponse.class,
                        designer.id,
                        designer.profileImage,
                        designer.name,
                        designer.introduction
                ))
                .from(designer)
                .where(designer.shop.id.eq(shopId), designer.isDeleted.eq(Boolean.FALSE))
                .fetch();

    }
}
