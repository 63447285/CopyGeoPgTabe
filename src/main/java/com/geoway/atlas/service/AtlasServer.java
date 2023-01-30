package com.geoway.atlas.service;

import java.util.Map;

/**
 * Atlas异步任务查询相关接口
 *
 * @author zhaotong 2022/9/7 9:59
 */
public interface AtlasServer {

    /**
     * 获取job结果
     *
     * @param jobId 任务id
     * @return 返回任务结果
     */
    Map<String, Object> jobResult(String jobId);

    /**
     * 停止任务
     *
     * @param jobId 任务id
     * @return 返回任务结果
     */
    Map<String, Object> canceljob(String jobId);
}
