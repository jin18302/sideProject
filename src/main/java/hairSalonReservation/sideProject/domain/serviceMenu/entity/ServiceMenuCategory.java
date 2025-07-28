package hairSalonReservation.sideProject.domain.serviceMenu.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity @Table(name = "service_menu_categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceMenuCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String name;

    private ServiceMenuCategory(String name){
       this.name = name;
    }

    public static ServiceMenuCategory from(String name){
        return new ServiceMenuCategory(name);
    }

    public void update(String name){
        this.name = name;
    }

    public void delete(){
        this.setDeleted(true);
        this.setDeletedAt(LocalDateTime.now());
    }
}
