package hairSalonReservation.sideProject.common.aop;

import hairSalonReservation.sideProject.common.annotation.CheckRole;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleCheck {

    private final HttpServletRequest httpRequest;

    @Before("@annotation(checkRole)")
    public void checkBefore(CheckRole checkRole) {
        String requiredRole = checkRole.value();
        String userRole = (String) httpRequest.getAttribute("userRole");

        if (userRole == null || !userRole.equalsIgnoreCase(requiredRole)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN);
        }
    }
}
