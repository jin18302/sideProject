package hairSalonReservation.sideProject.domain.shop.repository;

import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopRepositoryCustom {

    Page<ShopSummaryResponse> findByFilter(Pageable pageable, String area, List<String> tagList);


}
