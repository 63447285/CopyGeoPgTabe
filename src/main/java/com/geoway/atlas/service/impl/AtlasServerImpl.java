package com.geoway.atlas.service.impl;

import com.geoway.atlas.common.job.JobManager;
import com.geoway.atlas.service.AtlasServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Atlas任务执行信息
 *
 * @author zhaotong 2022/9/7 10:01
 */
@Service
public class AtlasServerImpl implements AtlasServer {

    @Autowired
    private JobManager jobManager;

    @Override
    public Map<String, Object> jobResult(String jobId) {
        return jobManager.getJobInfo(jobId);
    }

    @Override
    public Map<String, Object> canceljob(String jobId) {
        return jobManager.canceljob(jobId);
    }
}
