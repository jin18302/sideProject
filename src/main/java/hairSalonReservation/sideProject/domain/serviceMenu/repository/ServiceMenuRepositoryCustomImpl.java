package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.MenuCategory;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hairSalonReservation.sideProject.domain.serviceMenu.entity.QServiceMenu.serviceMenu;

@Repository
@RequiredArgsConstructor
public class ServiceMenuRepositoryCustomImpl implements ServiceMenuRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ServiceMenu> findByDesignerAndCategory(Long designerId, String category) {
        return jpaQueryFactory.select(serviceMenu)
                .where(
                        serviceMenu.category.eq(MenuCategory.valueOf(category)),
                        serviceMenu.designer.id.eq(designerId)
                ).fetch();
    }
}
