package hairSalonReservation.sideProject.domain.serviceMenu.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity @Table(name = "service_menus")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "designer_id")
    private Designer designer;

    @Enumerated(EnumType.STRING)
    private MenuCategory category;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    private ServiceMenu(Designer designer, MenuCategory category, String name, Integer price, String introduction){
        this.designer = designer;
        this.category = category;
        this.name = name;
        this.price = price;
        this.introduction = introduction;
    }

    public static ServiceMenu of(Designer designer, MenuCategory category, String name, Integer price, String introduction){
        return new ServiceMenu(designer, category, name, price, introduction);
    }

    public void update(String name, Integer price, String introduction){
        this.name = name;
        this.price = price;
        this.introduction = introduction;
    }

    public void delete(){
        this.setDeleted(true);
        this.setDeletedAt(LocalDateTime.now());
    }
}
