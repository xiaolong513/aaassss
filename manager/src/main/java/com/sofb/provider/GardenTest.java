package com.sofb.provider;

import com.sofb.feign.HellowWordFeign;
import com.sofb.feign.TeacherFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GardenTest {

    @Autowired
    private HellowWordFeign hellowWordFeign;

    @Autowired
    private TeacherFeign teacherFeign;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public boolean hello() {
        System.out.println("服务端收到请求");
        hellowWordFeign.hello();
        return false;
    }
}
