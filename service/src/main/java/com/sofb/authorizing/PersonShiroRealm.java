package com.sofb.authorizing;

import com.sofb.enums.UserStatusEnum;
import com.sofb.hr.LoginPersonInfo;
import com.sofb.hr.LoginPersonService;
import com.sofb.hr.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

//实现AuthorizingRealm接口 用户认证
public class PersonShiroRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private LoginPersonService loginPersonService;

    public PersonShiroRealm() {

    }

    public PersonShiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();

        //查询用户信息
        LoginPersonInfo personInfo = loginPersonService.getByName(name);

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : personInfo.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
        }
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(personInfo.getPermissions());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        LoginPersonInfo personInfo = loginPersonService.getByName(name);
        if (personInfo == null || personInfo.getPerson() == null) {
            //没找到帐号
            throw new UnknownAccountException();
        } else {
            //TODO
            if (personInfo.getPerson().getUserStatus() == UserStatusEnum.LOCKED) {
                //帐号锁定
                throw new LockedAccountException();
            }
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, personInfo.getPerson().getPassword(), getName());
            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(personInfo.getPerson().getCredentialsSalt()));
            return simpleAuthenticationInfo;
        }
    }
}
