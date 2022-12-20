package db.note;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String tableName() default "";

    String sgbd() default "postgresql";

    String database() default "tp_transversal_s5";

    String user() default "postgres";

    String password() default "0000";
}
