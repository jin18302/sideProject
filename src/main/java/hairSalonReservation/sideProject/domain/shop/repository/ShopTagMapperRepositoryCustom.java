package hairSalonReservation.sideProject.domain.shop.repository;

import java.util.List;

public interface ShopTagMapperRepositoryCustom {
    void deleteAllByShopTagIdIn(List<Long> shopTagIdList);
}
