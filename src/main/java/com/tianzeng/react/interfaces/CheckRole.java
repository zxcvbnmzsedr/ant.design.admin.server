package com.tianzeng.react.interfaces;

import java.lang.annotation.*;

/**
 * Created by tianzeng on 17-4-21.
 */
// 说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRole {
    String value() default "";
}
