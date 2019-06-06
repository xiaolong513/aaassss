package com.sofb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis.config")
@Component
@Data
public class RedisProperties {
    /**
     * 集群地址
     */
    private String clusterNodes;

    /**
     * 最大空闲连接数, 默认8个
     */
    private int maxIdle;

    /**
     * 最大连接数
     */
    private int maxTotal;

    /**
     * 最小连接数
     */
    private int minIdle;

    /**
     * 最大等待毫秒数
     */
    private int maxWaitMillis;

    /**
     * redis密码
     */
    private String password;

    /**
     * 链接超时时间
     */
    private int connectionTimeout;

    /**
     * 尝试次数
     */
    private int maxAttempts;

    /**
     * 网络超时时间
     */
    private int soTimeout;

}
