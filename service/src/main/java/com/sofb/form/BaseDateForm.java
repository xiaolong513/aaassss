package com.sofb.form;

import com.sofb.common.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDateForm implements Serializable {
    /**
     * 查询开始时间
     */
    private String beginDateStr;

    /**
     * 查询结束时间
     */
    private String endDateStr;

    public Date getBeginDate(){
        if(beginDateStr == null){
            return null;
        }
        return DateUtil.strToDate(beginDateStr, DateUtil.DATE_PATTERN);
    }

    public Date getEndDate(){
        if(endDateStr == null){
            return null;
        }
        return DateUtil.strToDate(endDateStr, DateUtil.DATE_PATTERN);
    }


}
