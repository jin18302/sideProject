package hairSalonReservation.sideProject.domain.review.entity;

import hairSalonReservation.sideProject.common.cursor.SortableField;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewSortField implements SortableField {
    CREATED_AT,
    RATING;
}
