package com.sofb.form;

import com.sofb.common.Pagination;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageForm extends BaseDateForm implements Serializable {
    /**
     * 当前页数
     */
    private int currentPage = 1;

    /**
     * 每页条数
     */
    private int pageSize = 30;


    public Pagination getPagination() {
        return new Pagination(pageSize, currentPage);
    }


}
