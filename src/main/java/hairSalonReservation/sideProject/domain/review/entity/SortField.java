package hairSalonReservation.sideProject.domain.review.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortField { //TODO: 엔티티마다 가능한 필드가 다를것이기때문에 이 부분 개선 팔요
    CREATED_AT,
    RATING;
}
