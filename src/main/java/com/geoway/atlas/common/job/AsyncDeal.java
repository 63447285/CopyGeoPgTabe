package com.geoway.atlas.common.job;

import java.lang.annotation.*;

/**
 * 异步任务执行注解
 * @author zhaotong 2022/9/6 16:06
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AsyncDeal {

    String value() default "";
}
