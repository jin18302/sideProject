package hairSalonReservation.sideProject.common.cursor;

import java.util.List;

public record CursorPageResponse<T>(List<T> content, Long lastCursor, boolean isLastPage) {

    public static <T> CursorPageResponse<T> of(List<T> content, Long lastCursor, boolean isLastPage){
        return new CursorPageResponse<T>(content, lastCursor,isLastPage);
    }
}