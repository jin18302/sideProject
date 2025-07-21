package hairSalonReservation.sideProject.domain.shop.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private ShopTag (String name){
        this.name = name;
    }

    public static ShopTag from(String name){
        return new ShopTag(name);
    }
}
