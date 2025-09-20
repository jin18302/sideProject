package hairSalonReservation.sideProject.domain.shop.entity;

import hairSalonReservation.sideProject.common.cursor.SortableField;
import lombok.Getter;

@Getter
public enum ShopSortField implements SortableField {

    CREATED_AT,
    LIKE_COUNT
}
