package com.sofb.hr;

import com.sofb.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "t_hr_person")
@Data
public class Person extends BaseEntity {

    public static Person newInstance() {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        return person;
    }

    @Column(name = "fId")
    @Id
    private String id;

    /**
     * 工号
     */
    @Column(name = "fNumber", columnDefinition = "varchar(20) COMMENT '工号'")
    private String number;

    /**
     * 登录账号
     */
    @Column(name = "fUserName", columnDefinition = "varchar(255) not null COMMENT '用户名'")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "fPassword", columnDefinition = "varchar(255) not null COMMENT '密码'")
    private String password;

    /**
     * 盐
     */
    @Column(name = "fSalt", columnDefinition = "varchar(255) not null COMMENT '盐'")
    private String salt;

    /**
     * 是否删除,是否禁用,是否锁定
     */
    @Column(name = "fUserStatus", columnDefinition = "varchar(255) COMMENT '用户状态'")
    private String userStatus;

    @Override
    public Object getFid() {
        return null;
    }

    public String getCredentialsSalt() {
        return userName + salt;
    }
}
