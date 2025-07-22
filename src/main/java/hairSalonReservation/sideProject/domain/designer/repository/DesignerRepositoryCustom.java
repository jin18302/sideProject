package hairSalonReservation.sideProject.domain.designer.repository;

import hairSalonReservation.sideProject.domain.designer.dto.response.DesignerSummaryResponse;

import java.util.List;

public interface DesignerRepositoryCustom {

    List<DesignerSummaryResponse> findByShopId(Long shopId);
}
