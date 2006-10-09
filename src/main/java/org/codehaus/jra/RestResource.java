package org.codehaus.jra;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RestResource {
    String value() default "";
    String uri() default "";
    String queryParameters() default "";
}
