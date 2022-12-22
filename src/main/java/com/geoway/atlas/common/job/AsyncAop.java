package com.geoway.atlas.common.job;

import com.geoway.atlas.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * 主要用于异步方法调用
 *
 * @author zhaotong
 * @version 1.0
 * @description
 * @date 2021/5/12 10:16
 */
@Aspect
@Component
@Slf4j
public class AsyncAop {

    @Autowired
    private JobManager jobManager;

    @Autowired
    @Qualifier(value = "asyncJobPool")
    private ExecutorService asyncJobPool;

    @Around("@annotation(asdel)")
    public Map<String, Object> asyncMethod(ProceedingJoinPoint point, AsyncDeal asdel) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes());
        RequestContextHolder.setRequestAttributes(requestAttributes, true);
        HttpServletRequest request = requestAttributes.getRequest();
        /** 获取任务的自增id */
        String jobId = jobManager.getJobId(request);

        Map<String, Object> response = ResponseBuilder.buildAsyncResponse(jobId);
        jobManager.putJob(jobId, ResponseBuilder.buildRunning(jobId));

        CompletableFuture<Map<String, Object>> future  = CompletableFuture.supplyAsync(() -> {
            try {
                return (Map<String, Object>) point.proceed(point.getArgs());
            } catch (Throwable throwable) {
                throw (RuntimeException) throwable;
            }
        }, asyncJobPool).whenComplete((objectMap, throwable) -> {
            if (throwable == null) {
                jobManager.putJob(jobId, objectMap);
                log.info("任务" + jobId + "成功!");
            } else {
                jobManager.putJob(jobId, ResponseBuilder.buildFailed(throwable));
                log.error("任务" + jobId + "失败!", throwable);
            }
            jobManager.removeFuture(jobId);
        });
        jobManager.putFuture(jobId, future);

        return response;
    }

}
