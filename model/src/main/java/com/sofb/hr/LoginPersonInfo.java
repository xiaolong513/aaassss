package com.sofb.hr;


import lombok.Data;

import java.util.List;

@Data
public class LoginPersonInfo {

    public LoginPersonInfo newInstance() {
        return new LoginPersonInfo();
    }

    public LoginPersonInfo() {
    }

    public LoginPersonInfo(Person person) {
        this.person = person;
    }


    /**
     * 人员
     */
    private Person person;

    /**
     * 角色
     */
    private List<Role> roles;

    /**
     * 角色
     */
    private List<Permission> permissions;

}
