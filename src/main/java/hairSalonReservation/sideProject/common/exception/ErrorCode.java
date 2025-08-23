package hairSalonReservation.sideProject.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //회원관련
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입되어있는 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "해당 기능을 수행할 권한이 없습니다."),

    //샵관련
    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 헤어샵입니다."),

    //샵 좋아요관련
    SHOP_FAVORITE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 좋아요입니다."),

    //태그관련
    SHOP_TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 태그입니다."),

    //카테고리 관련
    DUPLICATE_CATEGORY_NAME(HttpStatus.CONFLICT, "이미 존재하는 카테고리명입니다."),
    SERVICE_MENU_CATEGORY_NOTFOUND(HttpStatus.NOT_FOUND, "존재하지 않는 서비스카테고리입니다."),

    SERVICE_MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 메뉴입니다."),

    //디자이너 관련
    DESIGNER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 디자이너입니다."),

    //예약관련
    TIME_SLOT_ALREADY_BOOKED(HttpStatus.CONFLICT, "선택하신 시간은 예약이 불가능합니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 예약건입니다."),

    //리뷰관련
    REVIEW_NOT_ALLOWED_BEFORE_VISIT(HttpStatus.BAD_REQUEST, "방문 완료 후에만 리뷰 작성이 가능합니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다"),


    //이미지 관련
    MINIO_INITIALIZATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "버킷 설정 중 문제 발생"),
    IMAGE_UPLOAD_FAILED(HttpStatus.BAD_GATEWAY, "이미지 업로드 중 문제가 발생하였습니다"),
    IMAGE_VIEW_FAILED(HttpStatus.BAD_GATEWAY, "이미지를 불러오는 도중 문제가 발생했습니다"),
    SOFT_LOCK_CANT_ACCESS(HttpStatus.CONFLICT, "현재 다른요청을 처리중임으로 접근할수없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
