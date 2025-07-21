package hairSalonReservation.sideProject.domain.shop.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50)
    private String businessId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String imageUrlList;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String snsUriList;

    @Enumerated(EnumType.STRING)
    private ShopStatus shopStatus = ShopStatus.NOT_OPENED;

    private LocalDate openDate;

    private Shop(String name, String businessId, String address, String phoneNumber, LocalTime openTime, LocalTime endTime,
                 String introduction, String imageUrlList, String snsUriList, LocalDate openDate){
        this.name = name;
        this.businessId = businessId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openTime = openTime;
        this.endTime = endTime;
        this.introduction = introduction;
        this.imageUrlList = imageUrlList;
        this.snsUriList = snsUriList;
        this.openDate = openDate;
    }

    public static Shop of(String name, String businessId, String address, String phoneNumber, LocalTime openTime, LocalTime endTime,
                          String introduction, String imageUrlList, String snsUriList, LocalDate openDate){
        return new Shop(name, businessId, address, phoneNumber, openTime, endTime, introduction, imageUrlList, snsUriList, openDate);
    }
}
