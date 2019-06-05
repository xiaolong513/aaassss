package com.sofb.hr;

import com.sofb.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "t_hr_person_role")
@Data
public class PersonRoleRecord extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fId")
    @Id
    private Long id;

    @Column(name = "fPersonId", columnDefinition = "varchar(20) COMMENT '人员ID'")
    private String personId;

    @Column(name = "fRoleId", columnDefinition = "varchar(20) COMMENT '角色ID'")
    private String roleId;

/*    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "t_hr_role", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "id"))
    private List<Role> roles;*/

    @Override
    public Object getFid() {
        return id;
    }
}
