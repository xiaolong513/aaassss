package com.sofb.hr;

import com.sofb.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_hr_role_resource")
@Data
public class RoleResourceRecord extends BaseEntity {

    @GeneratedValue(generator = "brokerId")
    @GenericGenerator(name = "brokerId", strategy = "uuid")
    @Column(name = "fId")
    @Id
    private String id;

    @Column(name = "fkRoleId", columnDefinition = "bigint(20) not null COMMENT '角色ID'")
    private Long roleId;

    @Column(name = "fkResourceId", columnDefinition = "bigint(20) not null COMMENT '资源ID'")
    private Long resourceId;

    @Override
    public Object getFid() {
        return id;
    }
}
