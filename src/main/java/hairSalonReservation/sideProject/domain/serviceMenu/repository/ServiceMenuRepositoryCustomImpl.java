package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public List<ServiceMenu> findByServiceCategoryMapperId(Long serviceCategoryMapperId) {

        return jpaQueryFactory.select(serviceMenu)
                .from(serviceMenu)
                .where(serviceMenu.serviceMenuCategoryMapper.id.eq(serviceCategoryMapperId),
                        serviceMenu.isDeleted.eq(false))
                .fetch();
    }
}
