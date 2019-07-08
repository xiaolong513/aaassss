package com.sofb.feign.fallback;

import com.sofb.common.ServerResult;
import com.sofb.feign.StatisticHouseFeign;
import com.sofb.form.HouseBrokerStatisticsForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: tsx
 * @Version 1.0
 * @Description： 服务降级处理方法
 */
@Service
public class StatisticHouseFallback implements StatisticHouseFeign {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object listHouseData(Map<String, String> para) {
        logger.info("降级服务");
        return new ServerResult().success(new ArrayList<Object>(),"暂无数据");
    }

    @Override
    public Object listHouseGraphData(Map<String, String> para) {
        logger.info("降级服务");
        return new ServerResult().success(new ArrayList<Object>(),"暂无数据");
    }

    @Override
    public Object exportHouseListData(Map<String, String> para) {
        return null;
    }
}
