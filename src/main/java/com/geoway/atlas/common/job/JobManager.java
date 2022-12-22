package com.geoway.atlas.common.job;

import com.geoway.atlas.utils.ResponseBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步任务管理
 *
 * @author zhaotong 2022/9/6 16:59
 */

@Component
public class JobManager {

    public static final String JOB_ID = "jobid";

    public AtomicInteger autoId = new AtomicInteger(0);
    public Map<String, Map<String, Object>> taskMap = new HashMap<>();

    public Map<String, CompletableFuture<Map<String, Object>>> futureMap = new HashMap<>();

    /**
     * 获取jobId
     *
     * @param request 请求
     * @return 返回Job id
     */
    public String getJobId(HttpServletRequest request) {
        String jobId = request.getParameter(JOB_ID);
        if (StringUtils.isBlank(jobId)) {
            jobId = Integer.toString(autoId.getAndIncrement());
        }

        return jobId;
    }

    /**
     * 将任务信息
     *
     * @param jobId   任务id
     * @param jobInfo 任务信息
     */
    public void putJob(String jobId, Map<String, Object> jobInfo) {
        taskMap.put(jobId, jobInfo);
    }

    public void putFuture(String jobId, CompletableFuture<Map<String, Object>> future){
        futureMap.put(jobId, future);
    }

    public void removeFuture(String jobId){
        futureMap.remove(jobId);
    }
    /**
     * 获取job详细信息
     *
     * @param jobId 任务id
     * @return 返回任务详细信息
     */
    public Map<String, Object> getJobInfo(String jobId) {
        return taskMap.get(jobId);
    }

    public Map<String, Object> canceljob(String jobId){
        boolean isCancel = futureMap.get(jobId).cancel(true);
        return ResponseBuilder.buildSuccess("停止:" + ( isCancel ? "成功" : "失败"));
    }

}
