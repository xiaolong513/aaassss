package com.sofb.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "SOFB-DATA-CLEAN", path = "/sofb-data-clean")
public interface HellowWordFeign {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    boolean hello();

    @RequestMapping(value = "index", method = RequestMethod.GET)
    Object index();
}
