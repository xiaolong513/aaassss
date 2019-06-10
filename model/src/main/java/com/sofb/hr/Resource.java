package com.sofb.hr;

import com.sofb.BaseEntity;
import com.sofb.enums.ResourceTypeEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_hr_resource")
@Data
public class Resource extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fId")
    @Id
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "fRoleName", columnDefinition = "varchar(20) not null COMMENT '角色名称'")
    private String resourceName;

    /**
     * 菜单显示优先级
     */
    @Column(name = "fPriority", columnDefinition = "varchar(20) not null COMMENT '优先级'")
    private int priority;

    /**
     * 菜单层级
     */
    @Column(name = "fLevel", columnDefinition = "varchar(20) not null COMMENT '层级'")
    private int level;

    /**
     * 资源类型
     */
    @Column(name = "fResourceType", columnDefinition = "varchar(20) not null COMMENT '资源类型'")
    private ResourceTypeEnum resourceType;

    /**
     * 父编号
     */
    @Column(name = "fkParentId", columnDefinition = "varchar(20) not null COMMENT '上级ID'")
    private Long parentId;

    /**
     * 资源路径
     */
    @Column(name = "fUrl", columnDefinition = "varchar(20) not null COMMENT '资源路径'")
    private String url;


    @Override
    public Object getFid() {
        return getId();
    }
}
