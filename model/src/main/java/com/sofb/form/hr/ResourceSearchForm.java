package com.sofb.form.hr;

import com.sofb.enums.ResourceTypeEnum;
import com.sofb.form.BasePageForm;
import lombok.Data;

@Data
public class ResourceSearchForm extends BasePageForm {
    /**
     * 角色名称
     */
    private String resourceName;

    /**
     * 菜单显示优先级
     */
    private int priority;

    /**
     * 资源类型
     */
    private ResourceTypeEnum resourceType;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限字符串
     */
    private String permission;

    /**
     * 资源描述,UI界面显示使用
     */
    private String description;


}
