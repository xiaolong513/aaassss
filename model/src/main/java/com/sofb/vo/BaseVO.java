package com.sofb.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVO implements Serializable {

    /**
     * 状态
     */
    private String state;

    /**
     * 创建时间
     */
    private String createDate;
}
