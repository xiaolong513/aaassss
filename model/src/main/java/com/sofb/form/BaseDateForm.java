package com.sofb.form;

import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Date getBeginDate() {
        if (beginDateStr == null) {
            return null;
        }

        return strToDate(beginDateStr);
    }

    public Date getEndDate() {
        if (endDateStr == null) {
            return null;
        }
        return strToDate(endDateStr);
    }

    public Date strToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("BaseDateForm转换日期格式，异常");
        }
        return d;
    }


}
