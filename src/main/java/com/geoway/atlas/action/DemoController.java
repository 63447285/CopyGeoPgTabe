package com.geoway.atlas.action;

import com.geoway.atlas.common.job.AsyncDeal;
import com.geoway.atlas.service.DemoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试插件相关接口
 * @author zhaotong 2022/12/22 14:37
 */

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class DemoController {

    @Autowired
    private DemoServer demoServer;

    @AsyncDeal
    @GetMapping(value = {"/demo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> demo(@RequestParam(value = "duration") Integer seconds) {
        return demoServer.demo(seconds);
    }
}
