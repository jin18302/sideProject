package hairSalonReservation.sideProject.domain.shop.repository;

import hairSalonReservation.sideProject.domain.shop.entity.ShopFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopFavoriteRepository extends JpaRepository<ShopFavorite, Long> {
}
