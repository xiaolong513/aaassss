package com.sofb.feign;

import com.sofb.feign.fallback.StatisticHouseFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "SOFB-DATA-CLEAN")
public interface HellowWordFeign {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    boolean hello();

    @RequestMapping(value = "index", method = RequestMethod.GET)
    Object index();
}
