package com.geoway.atlas.action;

import com.geoway.atlas.common.exception.ExceptionDeal;
import com.geoway.atlas.service.AtlasServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Atlas异步调用相关接口
 *
 * @author zhaotong 2022/9/7 9:39
 */
@RestController
@CrossOrigin(
        origins = {"*"}
)
public class AtlasController {

    @Autowired
    private AtlasServer atlasServer;

    @ExceptionDeal
    @GetMapping(value = {"/atlas/jobresult"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> jobResult(@RequestParam(value = "jobid") String jobId) {
        return atlasServer.jobResult(jobId);
    }

    @ExceptionDeal
    @GetMapping(value = {"/atlas/canceljob"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> canceljob(@RequestParam(value = "jobid") String jobId) {
        return atlasServer.canceljob(jobId);
    }
}
