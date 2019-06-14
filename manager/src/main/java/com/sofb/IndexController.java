package com.sofb;

import com.sofb.hr.LoginPersonInfo;
import com.sofb.hr.LoginPersonService;
import com.sofb.hr.Resource;
import com.sofb.vo.MenuResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private LoginPersonService loginPersonService;


    @RequestMapping("/")
    public String index(Model model) {
        List<Resource> menus = null;
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/index")
    public String toIndex(Model model) {
        LoginPersonInfo loginPersonInfo = loginPersonService.getByCurrentPerson();
        List<MenuResourceVO> menus = null;
        model.addAttribute("menus", menus);
        return "index";
    }
}
