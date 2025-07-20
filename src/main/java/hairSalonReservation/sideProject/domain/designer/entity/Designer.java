package hairSalonReservation.sideProject.domain.designer.entity;

import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String imageUrlList;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String snsUriList;

    private Designer(Shop shop, String name, String introduction, String imageUrlList, String snsUriList){
        this.shop = shop;
        this.name = name;
        this.introduction = introduction;
        this.imageUrlList = imageUrlList;
        this.snsUriList = snsUriList;
    }

    public static Designer of(Shop shop, String name, String introduction, String imageUrlList, String snsUriList){
        return new Designer(shop, name, introduction, imageUrlList, snsUriList);
    }

}
