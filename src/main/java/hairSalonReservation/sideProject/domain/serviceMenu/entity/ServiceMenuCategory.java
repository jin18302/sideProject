package hairSalonReservation.sideProject.domain.serviceMenu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceMenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private ServiceMenuCategory(String name){
       this.name = name;
    }

    public static ServiceMenuCategory from(String name){
        return new ServiceMenuCategory(name);
    }
}
