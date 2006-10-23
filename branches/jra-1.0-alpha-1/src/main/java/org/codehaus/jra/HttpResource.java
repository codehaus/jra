package org.codehaus.jra;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HttpResource {
    String location() default "";
    String queryParameters() default "";
    String queryParameterSeparator() default "&";
    String inputSerialization() default "";
    String outputSerialization() default "";
}
