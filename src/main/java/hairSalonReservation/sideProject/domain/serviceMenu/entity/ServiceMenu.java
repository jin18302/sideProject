package hairSalonReservation.sideProject.domain.serviceMenu.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceMenuCategoryMapper serviceMenuCategoryMapper;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    private ServiceMenu(ServiceMenuCategoryMapper serviceMenuCategoryMapper, String name, Integer price, String introduction){
        this.serviceMenuCategoryMapper = serviceMenuCategoryMapper;
        this.name = name;
        this.price = price;
        this.introduction = introduction;
    }

    public static ServiceMenu of(ServiceMenuCategoryMapper serviceMenuCategoryMapper, String name, Integer price, String introduction){
        return new ServiceMenu(serviceMenuCategoryMapper, name, price, introduction);
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
