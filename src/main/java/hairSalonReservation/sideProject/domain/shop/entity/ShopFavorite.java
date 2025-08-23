package hairSalonReservation.sideProject.domain.shop.entity;

import hairSalonReservation.sideProject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "shop_favorites")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Shop shop;

    private ShopFavorite(User user, Shop shop){
        this.user = user;
        this.shop = shop;
    }

    public static ShopFavorite of(User user, Shop shop){
        return new ShopFavorite(user, shop);
    }
}
