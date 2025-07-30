package hairSalonReservation.sideProject.domain.designer.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import hairSalonReservation.sideProject.domain.shop.entity.Shop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity @Table(name = "designers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Designer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String dayOffWeekdayList;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String imageUrlList;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String snsUrlList;

    private Designer(Shop shop, String name, String profileImage, String introduction, String dayOffWeekdayList, String imageUrlList, String snsUriList){
        this.shop = shop;
        this.name = name;
        this.profileImage = profileImage;
        this.introduction = introduction;
        this.dayOffWeekdayList = dayOffWeekdayList;
        this.imageUrlList = imageUrlList;
        this.snsUrlList = snsUriList;
    }

    public static Designer of(Shop shop, String name, String profileImage, String introduction, String dayOffWeekdayList, String imageUrlList, String snsUriList){
        return new Designer(shop, name, profileImage, introduction, dayOffWeekdayList, imageUrlList, snsUriList);
    }

    public void update(String name, String profileImage, String introduction, String imageUrlList, String snsUriList){
        this.name = name;
        this.profileImage = profileImage;
        this.introduction = introduction;
        this.imageUrlList = imageUrlList;
        this.snsUrlList = snsUriList;
    }

    public void delete(){
        this.setDeleted(true);
        this.setDeletedAt(LocalDateTime.now());
    }
}
