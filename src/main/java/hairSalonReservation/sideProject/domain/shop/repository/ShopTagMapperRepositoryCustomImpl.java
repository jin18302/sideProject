package hairSalonReservation.sideProject.domain.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static hairSalonReservation.sideProject.domain.shop.entity.QShopTagMapper.shopTagMapper;

@Repository
@RequiredArgsConstructor
public class ShopTagMapperRepositoryCustomImpl implements ShopTagMapperRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public void deleteAllByShopTagIdIn(List<Long> shopTagIdList) {

        queryFactory.delete(shopTagMapper)
                .where(shopTagMapper.shopTag.id.in(shopTagIdList))
                .execute();
    }
}
