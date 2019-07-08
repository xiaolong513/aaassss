package com.sofb.feign;

import com.sofb.feign.fallback.StatisticBrokerFallback;
import com.sofb.feign.fallback.StatisticHouseFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: tsx
 * @Version 1.0
 * @Description：
 */
@Component
@FeignClient(name = "SOFB-DATA-CLEAN", contextId = "statisticBroker", fallback = StatisticBrokerFallback.class)
public interface StatisticBrokerFeign {
    @RequestMapping("/brokerStatistics/listBrokerData")
    Object listBrokerData(@RequestParam Map<String, String> para);

    @RequestMapping("/brokerStatistics/listBrokerGraphData")
    Object listBrokerGraphData(@RequestParam Map<String, String> para);

    @RequestMapping("/brokerStatistics/exportBrokerListData")
    Object exportBrokerListData(@RequestParam Map<String, String> para);
}
