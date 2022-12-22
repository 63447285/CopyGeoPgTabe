package com.geoway.atlas.common.serverCenter.zk;

import com.geoway.atlas.common.serverCenter.ServerHandle;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * zookeeper做注册中心
 * @author zhaotong 2022/10/11 19:05
 */
public class ZkServerHandle implements ServerHandle {

    public static final Logger log = LoggerFactory.getLogger(ZkServerHandle.class);

    public static final int RETRY_INTERVAL_MS = 3000;

    public static final int SESSION_TIMEOUT = 1000;

    public static final String NAME = "ZOOKEEPER";

    private final CuratorFramework curatorFramework;

    private final String zkRawPath;

    private NodeCache nodeCache;

    public ZkServerHandle(String zkIp, String zookPath) {
        RetryPolicy retryPolicy = new RetryForever(RETRY_INTERVAL_MS);
        this.zkRawPath = zookPath;
        this.curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString(zkIp)
                        .sessionTimeoutMs(SESSION_TIMEOUT)
                        .connectionTimeoutMs(SESSION_TIMEOUT)
                        .retryPolicy(retryPolicy)
                        .build();

        this.curatorFramework.start();
    }

    @Override
    public void register(String registerInfo) {
        byte[] registerInfoBytes = registerInfo.getBytes(StandardCharsets.UTF_8);
        try {
            // 如果存在节点则删除
            if (this.curatorFramework.checkExists().forPath(this.zkRawPath) != null) {
                this.curatorFramework.delete().forPath(this.zkRawPath);
                log.info("删除已存在节点:" + this.zkRawPath);
            }

            this.curatorFramework
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(this.zkRawPath, registerInfoBytes);
            log.info("服务注册成功:" + this.zkRawPath);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("注册zk服务中心异常!");
        }
    }

    /**
     * 监听zk节点，如果重新连接则重新创建数据节点
     *
     * @param registerInfo
     */
    public void monitor(String registerInfo) {

        nodeCache = new NodeCache(this.curatorFramework, this.zkRawPath);
        addListener(nodeCache, registerInfo);
        try {
            nodeCache.start();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("监控服务中心异常!");
        }
    }

    public void stop() {
        if (nodeCache != null) {
            try {
                nodeCache.close();
                log.info("关闭zk监听器!");
            } catch (IOException e) {
                log.error("关闭zk监听器错误!", e);
            }
        }
        if (curatorFramework != null) {
            curatorFramework.close();
            log.info("关闭zk客户端!");
        }

    }

    /**
     * 添加监听器
     *
     * @param nodeCache
     * @param registerInfo
     */
    public void addListener(NodeCache nodeCache, String registerInfo) {

        NodeCacheListener listener = () -> {
            ChildData childData = nodeCache.getCurrentData();
            if (childData == null) {
                log.info("节点被删除:" + nodeCache.getPath());
                register(registerInfo);
            }
        };
        nodeCache.getListenable().addListener(listener);
    }
}
