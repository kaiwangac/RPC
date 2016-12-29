package com.rpc.core.annotation;

import java.lang.annotation.*;

/**
 * Created by kaiwang on 2016/12/29.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Annotation {
    String path() default "";
}
