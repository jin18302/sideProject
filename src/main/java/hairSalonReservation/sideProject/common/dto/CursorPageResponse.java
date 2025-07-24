package hairSalonReservation.sideProject.common.dto;

import java.util.List;

public record CursorPageResponse<T>(List<T> content, Long lastCursor, boolean isLastPage) {

    public static <T> CursorPageResponse<T> of(List<T> content, Long lastCursor, boolean isLastPage){
        return new CursorPageResponse<T>(content, lastCursor,isLastPage);
    }
}