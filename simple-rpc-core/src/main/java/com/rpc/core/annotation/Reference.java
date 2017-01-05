package com.rpc.core.annotation;

import java.lang.annotation.*;

/**
 * Created by kaiwang on 2016/12/28.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Reference {
    String name() default "";
    String service() default "";
    String protocol() default "";
}
