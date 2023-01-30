package com.geoway.atlas.common.exception;

import com.geoway.atlas.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;

/**
 * 主要用于异步方法调用AOP
 *
 * @author zhaotong
 * @version 1.0
 * @description
 * @date 2021/5/12 10:16
 */
@Aspect
@Slf4j
public class ExceptionAop {

    @Around("@annotation(exdel)")
    public Map<String, Object> handleException(ProceedingJoinPoint point, ExceptionDeal exdel) {
        try {
            return (Map<String, Object>) point.proceed(point.getArgs());
        } catch (Throwable throwable) {
            return ResponseBuilder.buildFailed(throwable);
        }
    }

}
