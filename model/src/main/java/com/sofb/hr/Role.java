package com.sofb.hr;

import com.sofb.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_hr_role")
@Data
public class Role extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fId")
    @Id
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "fRoleName", columnDefinition = "varchar(20) COMMENT '角色名称'")
    private String roleName;

    /**
     * 角色描述,UI界面显示使用
     */
    @Column(name = "fDescription", columnDefinition = "varchar(2000) COMMENT '角色描述'")
    private String description;

    @Override
    public Object getFid() {
        return getId();
    }
}
