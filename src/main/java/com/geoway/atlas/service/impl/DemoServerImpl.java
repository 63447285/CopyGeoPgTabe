package com.geoway.atlas.service.impl;

import com.geoway.atlas.service.DemoServer;
import com.geoway.atlas.utils.ResponseBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 示例方法
 * @author zhaotong 2022/12/22 14:40
 */
@Service
public class DemoServerImpl implements DemoServer {
    @Override
    public Map<String, Object> demo(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException("线程终止!");
        }
        return ResponseBuilder.buildSuccess("运行成功!");
    }
}
