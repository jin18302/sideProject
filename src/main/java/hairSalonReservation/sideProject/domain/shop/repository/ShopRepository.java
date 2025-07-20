package hairSalonReservation.sideProject.domain.shop.repository;

import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
