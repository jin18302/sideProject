package hairSalonReservation.sideProject.domain.shop.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.common.dto.CursorPageResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;

import static hairSalonReservation.sideProject.domain.shop.entity.QShop.shop;
import static hairSalonReservation.sideProject.domain.shop.entity.QShopTag.shopTag;
import static hairSalonReservation.sideProject.domain.shop.entity.QShopTagMapper.shopTagMapper;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ShopRepositoryCustomImpl implements ShopRepositoryCustom {

    private final JPAQueryFactory queryFactory;

//    TODO: 지역별, 태그(선택), 선택한 예약시간에 예약가능한 샵 으로 필터링한 샵 조회
//          → 지역별 필터링, 태그로 필터링, 정렬기준(최신순, 저장순, 인기순),
//          커서페이지 네이션(이거 제대로 보여주려면, 좋아요나, 조회수, 인기순, 최신순)


    @Override
    public CursorPageResponse<ShopSummaryResponse> findByFilter(Long lastCursor, String area, List<Long> tagList) {

        List<ShopSummaryResponse> shopSummaryResponses = queryFactory.select(Projections.constructor(
                        ShopSummaryResponse.class,
                        shop.id,
                        shop.name,
                        shop.introduction,
                        shop.shopStatus,
                        shop.imageUrlList)
                ).from(shop)
                .join(shop.shopTagMapperList, shopTagMapper)
                .join(shopTagMapper.shopTag, shopTag)
                .where(shop.address.startsWith(area),
                        shopTag.id.in(tagList),
                        shop.id.gt(lastCursor)
                )
                .groupBy(shop.id)
                .having(shopTag.id.countDistinct().eq((long) tagList.size()))
                .limit(20)
                .orderBy(shop.createdAt.desc())
                .fetch();

        Long cursor = shopSummaryResponses.isEmpty() ? 0 : shopSummaryResponses.get(shopSummaryResponses.size() - 1).id();
        return CursorPageResponse.of(shopSummaryResponses, cursor, false);
    }
}
