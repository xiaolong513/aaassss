package com.sofb;


import com.sofb.common.ServerResult;
import com.sofb.enums.ServerResultCodeEnum;
import com.sofb.hr.Person;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {


    //post登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(Person person) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                person.getUserName(),
                person.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return new ServerResult().error(ServerResultCodeEnum.C0006);
        } catch (LockedAccountException e) {
            return new ServerResult().error(ServerResultCodeEnum.C0007);
        } catch (AuthenticationException e) {
            return new ServerResult().error(ServerResultCodeEnum.C0006);
        } catch (Exception e) {
            return new ServerResult().error(ServerResultCodeEnum.C0009);
        }

        if (subject.isAuthenticated()) {
            return new ServerResult().success(true);
        }
        return new ServerResult().error(ServerResultCodeEnum.C0009);
    }

    @RequestMapping(value = "/login")
    public Object login(HttpServletRequest req, Model model) {
        return new ServerResult().error(ServerResultCodeEnum.C0004);
    }


    //登出
    @RequestMapping(value = "/logout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ServerResult().success(true);
    }

}
