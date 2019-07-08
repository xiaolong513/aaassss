package com.sofb.feign;

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
@FeignClient(name = "SOFB-DATA-CLEAN", contextId = "statisticHouse", fallback = StatisticHouseFallback.class)
public interface StatisticHouseFeign {
    @RequestMapping("/houseStatistics/listHouseData")
    Object listHouseData(@RequestParam Map<String, String> para);

    @RequestMapping("/houseStatistics/listHouseGraphData")
    Object listHouseGraphData(@RequestParam Map<String, String> para);

    @RequestMapping("/houseStatistics/exportHouseListData")
    Object exportHouseListData(@RequestParam Map<String, String> para);
}
