package com.sofb.common;

import com.querydsl.jpa.impl.JPAQuery;

public class PageUtil {

    public static void setPageData(Pagination page, JPAQuery jpaQuery){
        if(page == null || jpaQuery == null){
            return;
        }
        page.setItems(jpaQuery.fetch());
        page.setRecordCount(jpaQuery.fetchCount());
    }

    public static void validPageSize(int pageSize){
        if(pageSize <= 0){
            throw new IllegalArgumentException("pageSize is illegal");
        }
    }

    public static void validOffset(int offset){
        if(offset < 0){
            throw new IllegalArgumentException("offset is illegal");
        }
    }
}
