package hairSalonReservation.sideProject.domain.shop.repository;

import hairSalonReservation.sideProject.domain.shop.entity.ShopTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopTagRepository extends JpaRepository<ShopTag, Long> {

    Optional<ShopTag> findByIdAndIsDeletedFalse(Long shopTagId);

    List<ShopTag> findAllByIsDeletedFalse();
}
