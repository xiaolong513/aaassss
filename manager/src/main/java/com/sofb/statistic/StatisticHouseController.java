package com.sofb.statistic;

import com.sofb.feign.StatisticHouseFeign;
import com.sofb.form.HouseBrokerStatisticsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tsx
 * @Date: 2019/7/5 15:13
 * @Version 1.0
 * @Description：
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/houseStatistics/*")
public class StatisticHouseController {
    @Autowired
    private StatisticHouseFeign statisticHouseFeign;

    @RequestMapping("listHouseData")
    public Object getHousesData(@ModelAttribute HouseBrokerStatisticsForm form) {
        Map<String, String> param = new HashMap<>();
        param.put("city", form.getCity().name());
        param.put("dataType", form.getDataType().name());
        param.put("beginDateStr", form.getBeginDateStr());
        param.put("endDateStr", form.getEndDateStr());
        Object object = statisticHouseFeign.listHouseData(param);
        return object;//new ServerResult().success(object);
    }

    @RequestMapping("listHouseGraphData")
    public Object listHouseGraphData(@ModelAttribute HouseBrokerStatisticsForm form) {
        Map<String, String> param = new HashMap<>();
        param.put("city", form.getCity().name());
        param.put("dataType", form.getDataType().name());
        Object object = statisticHouseFeign.listHouseGraphData(param);
        return object;
    }

    @RequestMapping("exportHouseListData")
    public Object exportHouseListData(@ModelAttribute HouseBrokerStatisticsForm form, HttpServletResponse response) {
        Map<String, String> param = new HashMap<>();
        param.put("city", form.getCity().name());
        param.put("dataType", form.getDataType().name());
        param.put("endDateStr", form.getEndDateStr());
        param.put("beginDateStr", form.getBeginDateStr());
        Object object = statisticHouseFeign.listHouseGraphData(param);
        return null;
    }

}
