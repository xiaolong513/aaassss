package com.sofb;


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
    public String login(Person person) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                person.getUserName(),
                person.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return "用户名/密码错误";
        } catch (LockedAccountException e) {
            return "账号被锁定";
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        if (subject.isAuthenticated()) {
            return "登录成功";
        }
        return "登录失败";
    }

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        return "未登录";
    }


    //登出
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }

}
