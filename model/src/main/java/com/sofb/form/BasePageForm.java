package com.sofb.form;

import com.sofb.common.Pagination;
import lombok.Data;

@Data
public class BasePageForm extends BaseDateForm {
    private Pagination pagination;

    /**
     * 当前页数
     */
    private int currentPage = 1;

    /**
     * 每页条数
     */
    private int pageSize = 30;


    public Pagination getPagination() {
        if (pagination == null) {
            pagination = new Pagination(pageSize, currentPage);
        }
        return pagination;
    }


}
