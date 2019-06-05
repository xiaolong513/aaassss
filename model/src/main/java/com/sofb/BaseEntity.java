package com.sofb;

import com.sofb.enums.CityEnum;
import com.sofb.enums.StateEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "fCreateDate", columnDefinition = "datetime not null default CURRENT_TIMESTAMP COMMENT '创建时间'", updatable = false)
    private Date createDate;

    @Column(name = "fModifyDate", columnDefinition = "timestamp COMMENT '修改时间'", insertable = false)
    private Date modifyDate;

    @Column(name = "fkCreatorId", columnDefinition = "varchar(255) not null COMMENT '创建人ID'", updatable = false)
    private String creatorId;

    @Column(name = "fkModifierId", columnDefinition = "varchar(255) COMMENT '修改人ID'", insertable = false)
    private String modifierId;

    @Column(name = "fState", columnDefinition = "varchar(10) not null  COMMENT '数据状态'")
    @Enumerated(EnumType.STRING)
    private StateEnum state = StateEnum.EFFECTIVE;

    @Column(name = "fCity", columnDefinition = "varchar(10) not null  COMMENT '城市'", updatable = false)
    @Enumerated(EnumType.STRING)
    private CityEnum city = CityEnum.SHENZHEN;

    public abstract Object getFid();


    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            setCreateDate(new Date());
        }
        if (StringUtils.isEmpty(creatorId)) {
        }
        if (state == null) {
            setState(StateEnum.EFFECTIVE);
        }

    }

    @PreUpdate
    public void preUpdate() {
        if (getModifyDate() == null) {
            setModifyDate(new Date());
        }
        if (StringUtils.isEmpty(getModifierId())) {
        }
    }

    @PostUpdate
    public void postUpdate() {
        if (getModifyDate() == null) {
            setModifyDate(new Date());
        }
        if (StringUtils.isEmpty(getModifierId())) {
        }
    }

}
