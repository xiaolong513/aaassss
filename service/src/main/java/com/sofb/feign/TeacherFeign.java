package com.sofb.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "HELLOCLIENT")
public interface TeacherFeign {

    @RequestMapping(value = "", method = RequestMethod.GET)
    Object getTeacher();
}
