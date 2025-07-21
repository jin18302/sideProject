package hairSalonReservation.sideProject.domain.shopTag.entity;

import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopTagMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShopTag shopTag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;


}
