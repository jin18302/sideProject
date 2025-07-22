package hairSalonReservation.sideProject.global.exception;

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

    //태그관련
    SHOP_TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 태그입니다."),

    //카테고리 관련
    DUPLICATE_CATEGORY_NAME(HttpStatus.CONFLICT, "이미 존재하는 카테고리명입니다."),
    SERVICE_MENU_CATEGORY_NOTFOUND(HttpStatus.NOT_FOUND, "존재하지 않는 서비스카테고리입니다."),

    //디자이너 관련
    DESIGNER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 디자이너입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
