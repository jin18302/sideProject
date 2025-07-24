package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenuCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hairSalonReservation.sideProject.domain.serviceMenu.entity.QServiceMenuCategory.serviceMenuCategory;
import static hairSalonReservation.sideProject.domain.serviceMenu.entity.QServiceMenuCategoryMapper.serviceMenuCategoryMapper;

@Repository
@RequiredArgsConstructor
public class ServiceMenuCategoryMapperRepositoryCustomImpl implements ServiceMenuCategoryMapperRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ServiceMenuCategoryMapper> findByDesignerId(Long designerId) {

        return queryFactory
                .selectDistinct(serviceMenuCategoryMapper)
                .from(serviceMenuCategoryMapper)
                .where(serviceMenuCategoryMapper.designer.id.eq(designerId))
                .join(serviceMenuCategoryMapper.serviceMenuCategory, serviceMenuCategory).fetchJoin()
                .fetch();
    }

    @Override
    public void deleteByDesignerIdAndCategoryIdIn(Long designerId, List<Long> categoryIdList) {

        queryFactory.delete(serviceMenuCategoryMapper)
                .where(
                        serviceMenuCategoryMapper.designer.id.eq(designerId),
                        serviceMenuCategoryMapper.serviceMenuCategory.id.in(categoryIdList)
                )
                .execute();
    }
}
