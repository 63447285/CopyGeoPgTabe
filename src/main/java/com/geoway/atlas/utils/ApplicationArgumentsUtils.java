package com.geoway.atlas.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;

import java.util.List;

/**
 * 应用参数处理类
 * @author zhaotong 2022/9/16 11:15
 */
public class ApplicationArgumentsUtils {

    private static final String NUM_EXECUTORS = "spark.num.executors";
    private static final String EXECUTOR_CORES = "spark.executors.cores";

    public static final String SERVER_CENTER = "server.center";

    public static final String DOMAIN_INFO = "domain.info";

    /**
     * 获取唯一值
     *
     * @param args 参数信息
     * @param key  键信息
     * @return 返回值信息
     */
    public static String getSingleValue(ApplicationArguments args, String key) {
        List<String> appNames = args.getOptionValues(key);
        if (CollectionUtils.isNotEmpty(appNames) && StringUtils.isNotEmpty(appNames.get(0))) {
            return appNames.get(0).trim();
        }
        return null;
    }

}
