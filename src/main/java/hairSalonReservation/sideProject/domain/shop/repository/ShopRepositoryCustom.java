package hairSalonReservation.sideProject.domain.shop.repository;

import com.querydsl.core.types.Order;
import hairSalonReservation.sideProject.domain.shop.entity.ShopSortField;
import hairSalonReservation.sideProject.common.cursor.CursorPageResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;

import java.util.List;

public interface ShopRepositoryCustom {

    CursorPageResponse<ShopSummaryResponse> findByFilter(String area, List<Long> tagList, ShopSortField sortField, Order order, String cursor);

    Long findShopOwnerIdByShopId(Long shopId);


}
