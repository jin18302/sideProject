package hairSalonReservation.sideProject.domain.serviceMenu.entity;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table(name = "service_category_mappers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceCategoryMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceMenuCategory serviceMenuCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private Designer designer;

    private ServiceCategoryMapper(ServiceMenuCategory serviceMenuCategory, Designer designer){
        this.serviceMenuCategory = serviceMenuCategory;
        this.designer = designer;
    }

    public static ServiceCategoryMapper of(ServiceMenuCategory serviceMenuCategory, Designer designer){
        return new ServiceCategoryMapper(serviceMenuCategory, designer);
    }
}
