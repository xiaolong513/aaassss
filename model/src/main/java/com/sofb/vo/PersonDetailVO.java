package com.sofb.vo;

import lombok.Data;

@Data
public class PersonDetailVO extends BaseVO {
    private String userName;

    private String phone;

    private String id;

    /**
     * 角色
     */
    private String roleName;

}
