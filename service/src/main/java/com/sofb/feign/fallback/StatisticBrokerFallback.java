package com.sofb.feign.fallback;

import com.sofb.common.ServerResult;
import com.sofb.enums.ServerResultCodeEnum;
import com.sofb.feign.StatisticBrokerFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author: tsx
 * @Version 1.0
 * @Description：
 */
public class StatisticBrokerFallback implements StatisticBrokerFeign {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object listBrokerData(Map<String, String> para) {
        logger.info("降级服务");
        return new ServerResult().error(ServerResultCodeEnum.C0009, "暂无数据");
    }

    @Override
    public Object listBrokerGraphData(Map<String, String> para) {
        logger.info("降级服务");
        return new ServerResult().error(ServerResultCodeEnum.C0009, "暂无数据");
    }

    @Override
    public Object exportBrokerListData(Map<String, String> para) {
        return null;
    }
}
