package hairSalonReservation.sideProject.common.enums;

import com.querydsl.core.types.OrderSpecifier;
import hairSalonReservation.sideProject.domain.review.entity.QReview;
import java.util.Arrays;

public enum SortType {
    CREATED_AT_DESC,
    CREATED_AT_ASC,
    RATING_DESC,
    RATING_ASC;

    public static SortType of(String sortType) {
        return Arrays.stream(hairSalonReservation.sideProject.common.enums.SortType.values())
                .filter(r -> r.name().equalsIgnoreCase(sortType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 sortType 입니다."));
    }

    public OrderSpecifier<?>[] getSortType(QReview r) {
        return switch (this) {
            case CREATED_AT_DESC      -> new OrderSpecifier[]{ r.createdAt.desc()};
            case CREATED_AT_ASC      -> new OrderSpecifier[]{ r.createdAt.asc()};
            case RATING_DESC -> new OrderSpecifier[]{ r.rating.desc()};
            case RATING_ASC  -> new OrderSpecifier[]{ r.rating.asc()};
        };
    }
}
