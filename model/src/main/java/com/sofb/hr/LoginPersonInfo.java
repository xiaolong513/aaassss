package com.sofb.hr;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
     * 资源
     */
    private List<Resource> resources;

    public List<String> getPermissions() {
        if (resources == null || resources.isEmpty()) {
            return new ArrayList<>();
        }
        return resources.stream().map(item -> item.getPermission()).collect(Collectors.toList());
    }

    public Set<String> getRoleNames() {
        if (roles == null || roles.isEmpty()) {
            return new HashSet<>();
        }
        return roles.stream().map(item -> item.getRoleName()).collect(Collectors.toSet());
    }

}
