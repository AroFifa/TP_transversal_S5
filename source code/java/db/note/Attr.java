package db.note;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Attr {
    String column() default "";

    boolean pk() default false;

    String sequence() default "";

    boolean fk() default false;

    String fk_column() default "";
}
