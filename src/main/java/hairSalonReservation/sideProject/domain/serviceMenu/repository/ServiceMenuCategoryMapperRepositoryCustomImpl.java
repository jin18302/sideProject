package hairSalonReservation.sideProject.domain.serviceMenu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hairSalonReservation.sideProject.domain.serviceMenu.entity.QServiceCategoryMapper.serviceCategoryMapper;
import static hairSalonReservation.sideProject.domain.serviceMenu.entity.QServiceMenuCategory.serviceMenuCategory;

@Repository
@RequiredArgsConstructor
public class ServiceMenuCategoryMapperRepositoryCustomImpl implements ServiceMenuCategoryMapperRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ServiceCategoryMapper> findByDesignerId(Long designerId) {

        return queryFactory
                .selectDistinct(serviceCategoryMapper)
                .from(serviceCategoryMapper)
                .where(serviceCategoryMapper.designer.id.eq(designerId))
                .join(serviceCategoryMapper.serviceMenuCategory, serviceMenuCategory).fetchJoin()
                .fetch();
    }

    @Override
    public void deleteByDesignerIdAndCategoryIdIn(Long designerId, List<Long> categoryIdList) {

        queryFactory.delete(serviceCategoryMapper)
                .where(
                        serviceCategoryMapper.designer.id.eq(designerId),
                        serviceCategoryMapper.serviceMenuCategory.id.in(categoryIdList)
                )
                .execute();
    }
}
