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

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false)
    boolean isDeleted = false;

    private ShopTag (String name){
        this.name = name;
    }

    public static ShopTag from(String name){
        return new ShopTag(name);
    }

    public void delete(){
        this.isDeleted = true;
    }
}
