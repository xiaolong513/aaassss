package com.sofb.form.hr;

import com.sofb.form.BasePageForm;
import lombok.Data;

@Data
public class RoleSearchForm extends BasePageForm {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;
}
