package com.sofb;

import com.sofb.hr.Resource;
import com.sofb.hr.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Resource> menus = null;
        model.addAttribute("menus", menus);
        return "index";
    }
}
