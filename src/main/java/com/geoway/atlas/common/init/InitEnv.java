package com.geoway.atlas.common.init;

import com.geoway.atlas.common.serverCenter.ServerCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化注册服务
 * @author zhaotong 2022/12/22 14:43
 */
@Component
@Slf4j
public class InitEnv implements ApplicationRunner {

    @Autowired
    private ServerCenter serverCenter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(
                "接收到所有参数为:" + String.join(",", args.getSourceArgs()));
        serverCenter.init();
        log.info("服务准备就绪!");
    }
}
