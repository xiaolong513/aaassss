package com.sofb.authorizing;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "shiro.config")
@Component
@Data
public class ShiroProperties {
    /**
     * 登录的url
     */
    private String loginUrl;

    /**
     * 登录成功后跳转的url
     */
    private String successUrl;

    /**
     * 未授权 url
     */
    private String unauthorizedUrl;

    /**
     * 免认证 url
     */
    private String anonUrl;

    /**
     * 登出的url
     */
    private String logoutUrl;

}
