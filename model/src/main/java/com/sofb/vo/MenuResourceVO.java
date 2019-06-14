package com.sofb.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuResourceVO implements Serializable {
    /**
     * 显示名称
     */
    private String name;

    /**
     * 链接
     */
    private String url;

    /**
     * 权限
     */
    private String[] permissions = new String[]{};

    private List<String> permissionList = new ArrayList<>();

    /**
     * 子菜单
     */
    private List<MenuResourceVO> childMenuResources;

    public void addPersmission(String permission) {
        permissionList.add(permission);
    }

    public String[] getPermissions() {
        return permissionList.toArray(permissions);
    }

}
