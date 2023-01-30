package com.geoway.atlas.common.exception;

import java.lang.annotation.*;

/**
 * @author zhaotong 2022/9/6 16:06
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExceptionDeal {

    String value() default "";
}
