package com.geoway.atlas.common.serverCenter;

/**
 * 服务中心注册接口
 *
 * @author zhaotong 2022/10/11 19:25
 */
public interface ServerHandle {

    /**
     * 注册服务信息
     *
     * @param registerInfo 服务信息
     */
    void register(String registerInfo);

    /**
     * 监控注册信息，如果不存在则创建
     *
     * @param registerInfo 服务信息
     */
    void monitor(String registerInfo);

    /**
     * 停止监听
     */
    void stop();
}
