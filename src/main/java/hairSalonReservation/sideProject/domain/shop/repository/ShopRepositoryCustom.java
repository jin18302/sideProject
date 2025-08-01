package hairSalonReservation.sideProject.domain.shop.repository;

import hairSalonReservation.sideProject.common.dto.CursorPageResponse;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ShopRepositoryCustom {

    CursorPageResponse<ShopSummaryResponse> findByFilter(Long lastCursor, String area, List<Long> tagList);

    Long findShopOwnerIdByShopId(Long shopId);


}
