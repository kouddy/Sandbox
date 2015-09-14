package composition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Export {
String name() default "";

Class<?> type() default Object.class;
}
