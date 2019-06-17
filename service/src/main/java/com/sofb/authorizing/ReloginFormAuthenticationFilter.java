package com.sofb.authorizing;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ReloginFormAuthenticationFilter extends FormAuthenticationFilter {
    public ReloginFormAuthenticationFilter() {
        this.setUsernameParam("userName");
        this.setPasswordParam("password");
        this.setRememberMeParam("rememberMe");
        this.setLoginUrl("/login");
    }

    /**
     * 每次登录都会到这里来，这里用来处理 不注销之前已登录用户下，再次登录
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return false;
            } else {
                return true;
            }
        }

        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 重写FormAuthenticationFilter的onLoginSuccess方法
     * 指定的url传递进去，这样就实现了跳转到指定的页面；
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);//清理了session中保存的请求信息
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
        return false;
    }


}
