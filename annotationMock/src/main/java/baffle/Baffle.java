package baffle;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.METHOD , ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Baffle {
    boolean value() default true;
}
