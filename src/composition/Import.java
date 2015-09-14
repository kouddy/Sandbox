package composition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Import {
String name() default "";

Class<?> type() default Object.class;
}
