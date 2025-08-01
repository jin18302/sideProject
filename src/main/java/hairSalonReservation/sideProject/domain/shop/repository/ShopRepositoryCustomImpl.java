package hairSalonReservation.sideProject.domain.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.common.dto.CursorPageResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
import static hairSalonReservation.sideProject.domain.shop.entity.QShop.shop;
import static hairSalonReservation.sideProject.domain.shop.entity.QShopTagMapper.shopTagMapper;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ShopRepositoryCustomImpl implements ShopRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Value("${query.limit}")
    private int limit;

    @Override
    public CursorPageResponse<ShopSummaryResponse> findByFilter(Long cursor, String area, List<Long> tagList) {

        List<Long> taggedShopList = tagList == null ? null : filterShopIdsByAllTagsAndCursor(cursor, tagList);

        BooleanBuilder builder = new BooleanBuilder().and(shop.address.startsWith(area));
        if(taggedShopList != null){builder.and(shop.id.in(taggedShopList).and(shop.id.lt(cursor)));}

        List<ShopSummaryResponse> shopSummaryResponseList = queryFactory.select(
                        Projections.constructor(
                                ShopSummaryResponse.class,
                                shop.id,
                                shop.name,
                                shop.introduction,
                                shop.address,
                                shop.shopStatus,
                                shop.imageUrlList
                        ))
                .from(shop)
                .where(builder)
                .orderBy(shop.id.desc())
                .limit(limit + 1)
                .fetch();

        boolean isLast = shopSummaryResponseList.size() < limit + 1;
        if(!isLast){shopSummaryResponseList.remove(limit);}

        Long lastCursor = shopSummaryResponseList.isEmpty() ? 0 : shopSummaryResponseList.get(shopSummaryResponseList.size() - 1).getId();
        return new CursorPageResponse<>(shopSummaryResponseList, lastCursor, isLast);
    }

    private List<Long> filterShopIdsByAllTagsAndCursor(Long lastCursor, List<Long> tagList){
        BooleanBuilder subQueryBuilder = new BooleanBuilder().and(shopTagMapper.shopTag.id.in(tagList));
        if(lastCursor != 0){subQueryBuilder.and(shop.id.lt(lastCursor));}

       return  queryFactory.select(shopTagMapper.shop.id)
                .from(shopTagMapper)
                .where(subQueryBuilder)
                .groupBy(shopTagMapper.shop.id)
                .having(shopTagMapper.shopTag.id.countDistinct().eq((long) tagList.size()))
                .fetch();
    }

    @Override
    public Long findShopOwnerIdByShopId(Long shopId) {
        return queryFactory.select(shop.user.id)
                .from(shop)
                .where(shop.id.eq(shopId))
                .fetchOne();
    }
}
