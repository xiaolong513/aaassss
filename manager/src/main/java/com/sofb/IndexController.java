package com.sofb;

import com.sofb.common.ServerResult;
import com.sofb.hr.LoginPersonInfo;
import com.sofb.hr.LoginPersonService;
import com.sofb.voconvert.MenuResourceVOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private LoginPersonService loginPersonService;

    @RequestMapping("/")
    public Object index(Model model) {
        LoginPersonInfo loginPersonInfo = loginPersonService.getByCurrentPerson();
        return new ServerResult().success(MenuResourceVOConvertUtil.convertMenuResourceVO(loginPersonInfo.getResources()));
    }

    @RequestMapping("/index")
    public Object toIndex(Model model) {
        LoginPersonInfo loginPersonInfo = loginPersonService.getByCurrentPerson();
        return new ServerResult().success(MenuResourceVOConvertUtil.convertMenuResourceVO(loginPersonInfo.getResources()));
    }
}
