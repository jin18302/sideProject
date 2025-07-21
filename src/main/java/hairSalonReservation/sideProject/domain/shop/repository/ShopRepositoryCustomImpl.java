package hairSalonReservation.sideProject.domain.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hairSalonReservation.sideProject.domain.shop.dto.response.ShopSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryCustomImpl implements ShopRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<ShopSummaryResponse> findByFilter(Pageable pageable, String area, List<String> tagList) {
        return null;
    }
}
