package hairSalonReservation.sideProject.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CheckRole {
    String value();
}
