package com.sofb.vo;

import lombok.Data;

import java.io.Serializable;
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
    private String permission;

    /**
     * 子菜单
     */
    private List<MenuResourceVO> childMenuResources;
}
