package hairSalonReservation.sideProject.domain.shopTag.repository;

import hairSalonReservation.sideProject.domain.shopTag.entity.ShopTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopTagMapperRepository extends JpaRepository<ShopTag, Long> {
}
