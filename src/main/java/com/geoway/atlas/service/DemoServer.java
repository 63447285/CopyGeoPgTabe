package com.geoway.atlas.service;

import java.util.Map;

/**
 * 示例服务接口
 * @author zhaotong 2022/12/22 14:39
 */
public interface DemoServer {

    Map<String, Object> demo(int seconds);
}
