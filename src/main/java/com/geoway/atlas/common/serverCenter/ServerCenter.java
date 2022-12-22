package com.geoway.atlas.common.serverCenter;

import com.geoway.atlas.AtlasRestfulApplication;
import com.geoway.atlas.common.serverCenter.zk.ZkServerHandle;
import com.geoway.atlas.utils.ApplicationArgumentsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

/**
 * @author zhaotong 2022/10/11 18:53
 */

@Component
@Slf4j
public class ServerCenter {

    private ServerHandle serverHandle;

    @Autowired
    private ApplicationArguments args;

    /**
     * 初始化连接
     */
    public void init() {
        String centerServer = ApplicationArgumentsUtils.getSingleValue(args, ApplicationArgumentsUtils.SERVER_CENTER);
        String domainInfo = ApplicationArgumentsUtils.getSingleValue(args, ApplicationArgumentsUtils.DOMAIN_INFO);
        if (StringUtils.isNotBlank(centerServer)) {
            URI centerUri;
            try {
                centerUri = new URI(centerServer);
            } catch (URISyntaxException e) {
                throw new RuntimeException("无法解析服务中心路径");
            }

            String schema = centerUri.getScheme();
            switch (schema.toUpperCase()) {
                case ZkServerHandle.NAME:
                    serverHandle = new ZkServerHandle(centerUri.getAuthority(), centerUri.getPath());
                    break;
                default:
                    throw new RuntimeException("不支持指定的服务中心类型:" + schema);
            }

            String registerInfo = getRegisterInfo(domainInfo);

            serverHandle.register(registerInfo);
            serverHandle.monitor(registerInfo);
        }
    }

    /**
     * 获取注册信息
     * @param domainInfo 自定义的地址
     * @return 返回注册的信息
     */
    public String getRegisterInfo(String domainInfo) {
        String registerInfo;

        if (StringUtils.isNotBlank(domainInfo)) {
            registerInfo = domainInfo;
        } else {
            String serverAddress =
                    ApplicationArgumentsUtils.getSingleValue(args, AtlasRestfulApplication.SERVER_ADDRESS);
            if (StringUtils.isBlank(serverAddress)) {
                try {
                    serverAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("未知主机名:" + e.getMessage(), e);
                    throw new RuntimeException("未知主机名:" + e.getMessage());
                }
            }
            String port = ApplicationArgumentsUtils.getSingleValue(args, AtlasRestfulApplication.SERVER_PORT);

            registerInfo = serverAddress + ":" + port;
        }

        return registerInfo;
    }

    @PreDestroy
    public void stop() {
        serverHandle.stop();
    }

}
