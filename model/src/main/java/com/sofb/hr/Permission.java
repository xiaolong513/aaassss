package com.sofb.hr;

import com.sofb.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_hr_persmission")
@Data
public class Permission extends BaseEntity {

    @GeneratedValue(generator = "personId")
    @GenericGenerator(name = "personId", strategy = "uuid")
    @Column(name = "fId")
    @Id
    private String id;

    @Column(name = "fPermission", columnDefinition = "varchar(20) COMMENT '权限'")
    private String permission;

    @Override
    public Object getFid() {
        return null;
    }
}
