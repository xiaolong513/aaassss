package com.sofb.common;


import com.sofb.enums.SortEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页排序类
 */
@Data
public class Pagination<T> implements Serializable {
    public static final int DEFAULT_CURRENT_PAGE = 1;
    public static int DEFAULT_PAGE_SIZE = 30;
    private List<T> items;
    private long recordCount;
    private int pageSize = DEFAULT_PAGE_SIZE;

    private int currentPageStartIndex = 0;
    private int currentPage;

    /**
     * 排序方式
     */
    private SortEnum sort = SortEnum.DESC;

    /**
     * 排序字段
     */
    private String sortField;

    public Pagination() {
        this(DEFAULT_PAGE_SIZE, DEFAULT_CURRENT_PAGE);
    }

    /**
     * 用于直查询条数用
     *
     * @return
     */
    public static <T> Pagination<T> getZeroPageSizePagination() {
        final Pagination<T> page = new Pagination<T>();
        page.pageSize = 0;
        return page;
    }

    public Pagination(int pageSize, int currentPage) {
        if (pageSize < 1) {
            throw new IllegalArgumentException("Count should be greater than zero!");
        } else if (currentPage < 1) {
            this.currentPage = 1;
        } else {
            this.pageSize = pageSize;
            this.currentPage = currentPage;
        }
    }

    public int getOffSet() {
        return (currentPage - 1) * pageSize;
    }

    public String getCount() {
        return this.getRecordCount() + "";
    }

    public List<T> getItems() {
        if (null == this.items) {
            this.items = new ArrayList();
        }
        return items;
    }

    public Pagination setItems(List<T> list) {
        if (list == null) {
            this.items = new ArrayList();
        }else {
            this.items = list;
        }
        return this;
    }

    public long getPageCount() {
        return (recordCount == 0) ? 1 : ((recordCount % pageSize == 0) ? (recordCount / pageSize)
                : (recordCount / pageSize) + 1);
    }


}