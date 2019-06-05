package com.sofb.hr;

import com.sofb.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_hr_role_permission")
@Data
public class RolePermissionRecord extends BaseEntity {

    @GeneratedValue(generator = "brokerId")
    @GenericGenerator(name = "brokerId", strategy = "uuid")
    @Column(name = "fId")
    @Id
    private int id;

    @Column(name = "fRoleId", columnDefinition = "varchar(20) COMMENT '角色ID'")
    private String roleId;

    @Column(name = "fPermissionId", columnDefinition = "varchar(20) COMMENT '权限ID'")
    private String permissionId;

    @Override
    public Object getFid() {
        return id;
    }
}
