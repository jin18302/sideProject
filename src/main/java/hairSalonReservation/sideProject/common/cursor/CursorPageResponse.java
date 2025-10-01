package hairSalonReservation.sideProject.common.cursor;

import hairSalonReservation.sideProject.common.config.QueryProperties;

import java.util.List;
import java.util.function.Function;

public record CursorPageResponse<T>(List<T> content, Long lastCursor, boolean isLastPage) {

    public static <T> CursorPageResponse<T> of(List<T> contentList, Function<T, Long> idExtractor) { //

        boolean isLast = contentList.size() < new QueryProperties().getLimit() + 1;
        if (!isLast) {contentList.remove(contentList.size() - 1);}

        Long lastCursor = idExtractor.apply(contentList.get(contentList.size() - 1));
        return new CursorPageResponse<T>(contentList, lastCursor, isLast);
    }
}