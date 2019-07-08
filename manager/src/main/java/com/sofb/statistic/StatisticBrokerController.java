package com.sofb.statistic;

import com.sofb.feign.StatisticBrokerFeign;
import com.sofb.form.HouseBrokerStatisticsForm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tsx
 * @Version 1.0
 * @Description：
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/brokerStatistics/*")
public class StatisticBrokerController {

    private StatisticBrokerFeign statisticBrokerFeign;

    @RequestMapping("listBrokerData")
    public Object getBrokers(@ModelAttribute HouseBrokerStatisticsForm form) {
        Map<String, String> paramBroker = new HashMap<>();
        paramBroker.put("beginDateStr", form.getBeginDateStr());
        paramBroker.put("endDateStr", form.getEndDateStr());
        paramBroker.put("city", form.getCity().name());
        paramBroker.put("dataType", form.getDataType().name());
        Object object = statisticBrokerFeign.listBrokerData(paramBroker);
        return object;
    }

    @RequestMapping("listBrokerGraphData")
    public Object listBrokerGraphData(@ModelAttribute HouseBrokerStatisticsForm form) {
        Map<String, String> param = new HashMap<>();
        param.put("city", form.getCity().name());
        param.put("dataType", form.getDataType().name());
        Object object = statisticBrokerFeign.listBrokerGraphData(param);
        return object;
    }

    @RequestMapping("exportHouseListData")
    public Object exportHouseListData(@ModelAttribute HouseBrokerStatisticsForm form, HttpServletResponse response) {
        Map<String, String> param = new HashMap<>();
        param.put("city", form.getCity().name());
        param.put("endDateStr", form.getEndDateStr());
        param.put("beginDateStr", form.getBeginDateStr());
        param.put("dataType", form.getDataType().name());
        Object object = statisticBrokerFeign.exportBrokerListData(param);
        return null;
    }
}
