package hairSalonReservation.sideProject.domain.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.common.cursor.CursorStrategy;
import hairSalonReservation.sideProject.domain.shop.entity.ShopSortField;
import hairSalonReservation.sideProject.common.cursor.CursorPageResponse;
import hairSalonReservation.sideProject.common.cursor.OrderSpecifierFactory;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import hairSalonReservation.sideProject.domain.shop.entity.QShop;
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
    private final CursorStrategy<QShop, ShopSortField> cursorStrategy;
    private final OrderSpecifierFactory<QShop, ShopSortField> orderSpecifierFactory;

    @Value("${query.limit}")
    private int limit;//TODO

    @Override
    public CursorPageResponse<ShopSummaryResponse> findByFilter(String area, List<Long> tagList, ShopSortField sortField, Order order, String cursor) {

        List<Long> taggedShopList = tagList == null ? null : filterShopIdsByAllTagsAndCursor(cursor, tagList, order, sortField);

        BooleanBuilder builder = new BooleanBuilder().and(shop.address.startsWith(area));
        if(taggedShopList != null){builder.and(shop.id.in(taggedShopList));}

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
                .orderBy(orderSpecifierFactory.generateOrderSpecifier(shop, sortField, order))
                .limit(limit + 1)
                .fetch();

        boolean isLast = shopSummaryResponseList.size() < limit + 1;
        if(!isLast){shopSummaryResponseList.remove(limit);}

        Long lastCursor = shopSummaryResponseList.isEmpty() ? 0 : shopSummaryResponseList.get(shopSummaryResponseList.size() - 1).getId();
        return new CursorPageResponse<>(shopSummaryResponseList, lastCursor, isLast);
    }



    private List<Long> filterShopIdsByAllTagsAndCursor(String lastCursor, List<Long> tagList, Order order, ShopSortField sortField){

        BooleanBuilder subQueryBuilder = new BooleanBuilder().and(shopTagMapper.shopTag.id.in(tagList));
        if(lastCursor != null){
            subQueryBuilder.and(cursorStrategy.buildCursorPredicate(shop, lastCursor, order, sortField));
        }

       return  queryFactory.select(shopTagMapper.shop.id)
                .from(shopTagMapper)
                .where(subQueryBuilder)
                .groupBy(shopTagMapper.shop.id)
                .having(shopTagMapper.shopTag.id.count().eq((long) tagList.size()))
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
