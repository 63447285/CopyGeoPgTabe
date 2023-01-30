package com.geoway.atlas.common.job;

import com.geoway.atlas.utils.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * job线程池
 * @author zhaotong 2022/9/6 18:08
 */

@Component
public class JobPool {

    @Value("${async.job.threads.num}")
    private Integer nThread;

    /**
     * 创建job异步线程池
     */
    @Bean(value = "asyncJobPool", destroyMethod = "shutdownNow")
    public ExecutorService getThreadPool() {
        return Executors.newFixedThreadPool(nThread, ThreadFactoryBuilder.getNamedThreadFactory("asyncJobPool"));
    }

}
