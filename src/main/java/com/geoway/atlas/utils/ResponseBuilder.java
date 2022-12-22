package com.geoway.atlas.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.*;

/**
 * 异步返回结果构造器
 *
 * @author zhaotong 2022/9/6 16:20
 */
public class ResponseBuilder {

    public static final String STATE = "state";
    public static final String ERROR = "error";
    public static final String TIMEOUT_MILLIS = "timeoutMillis";
    public static final String JOB_ID = "jobid";
    public static final String PROGRESS = "progress";
    public static final String RESULT = "result";

    public static final Integer DEFAULT_TIMEOUT_VALUE = 36000000;
    public static final String STATE_SUCCEED = "succeed";
    public static final String STATE_FAILED = "failed";
    public static final String STATE_RUNNING = "running";

    /**
     * 构建失败的任务返回信息
     *
     * @param throwable 错误信息
     * @return 返回失败信息
     */
    public static Map<String, Object> buildFailed(Throwable throwable) {
        List<String> errorList = new ArrayList<>();
        Map<String, Object> errorObj = new HashMap<>();
        errorList.addAll(Arrays.asList(ExceptionUtils.getRootCauseStackTrace(throwable)));

        errorObj.put(STATE, STATE_FAILED);
        errorObj.put(ERROR, errorList);

        return errorObj;
    }

    /**
     * 构建运行中任务信息
     *
     * @param jobId 任务id
     * @return 返回失败信息
     */
    public static Map<String, Object> buildRunning(String jobId) {
        Map<String, Object> responseObj = new HashMap<>();

        responseObj.put(STATE, STATE_RUNNING);
        responseObj.put(PROGRESS, 50);

        return responseObj;
    }

    /**
     * 构建运行成功任务信息
     *
     * @param message 成功构建任务消息
     * @return 返回失败信息
     */
    public static Map<String, Object> buildSuccess(Object message) {
        Map<String, Object> responseObj = new HashMap<>();

        responseObj.put(STATE, STATE_SUCCEED);
        responseObj.put(RESULT, message);

        return responseObj;
    }

    /**
     * 构建异步返回的响应信息
     *
     * @param jobId 任务id
     * @return 返回失败信息
     */
    public static Map<String, Object> buildAsyncResponse(String jobId) {
        Map<String, Object> responseObj = new HashMap<>();

        responseObj.put(STATE, STATE_RUNNING);
        responseObj.put(JOB_ID, jobId);
        responseObj.put(TIMEOUT_MILLIS, DEFAULT_TIMEOUT_VALUE);

        return responseObj;
    }
}
