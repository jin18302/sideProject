package hairSalonReservation.sideProject.domain.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_shop_tag", columnNames = {"shop", "shop_tag"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ShopTagMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShopTag shopTag;



    private ShopTagMapper(ShopTag shopTag, Shop shop){
        this.shop = shop;
        this.shopTag = shopTag;
        shop.getShopTagMapperList().add(this);
    }

    public static ShopTagMapper of(ShopTag shopTag, Shop shop){
        return new ShopTagMapper(shopTag, shop);
    }
}
