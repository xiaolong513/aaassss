package com.sofb;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sofb.common.CollectionUtil;
import com.sofb.common.StringUtil;
import com.sofb.enums.SortEnum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Transactional
public class BaseService {
    protected final int defaultPageSize = 999;
    protected final int defaultOffSet = 0;
    @Autowired
    protected JPAQueryFactory jpaQueryFactory;

    @Autowired
    protected EntityManager entityManager;


    private static final ConcurrentMap<Class<?>, String> class_table = new ConcurrentHashMap<>();

    public <T> T insert(T t) {
        if (t == null) {
            return null;
        }
        entityManager.persist(t);
        return t;
    }

    public <T> boolean insert(List<T> list) {
        if (CollectionUtil.isEmpty(list)) {
            return false;
        }
        for (T t : list) {
            entityManager.persist(t);
        }

        return true;
    }

    public <T> boolean batchDeleteByDomian(List<T> tList) {
        if (CollectionUtil.isEmpty(tList)) {
            return false;
        }
        for (T t : tList) {
            deleteByDomian(t);
        }

        return true;
    }

    public <T> boolean deleteByDomian(T t) {
        if (t == null) {
            return false;
        }
        String tableName = getTableName(t);
        if (StringUtil.isEmpty(tableName)) {
            return false;
        }
        String sql = "delete from " + tableName + " where fId = ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, t);
        return query.executeUpdate() > 0;
    }

    private <T> String getTableName(T t) {
        if (t == null) {
            return null;
        }
        String tableName = class_table.get(t.getClass());
        if (StringUtil.isEmpty(tableName)) {
            Table table = t.getClass().getAnnotation(Table.class);
            if (table != null) {
                tableName = table.name();
                class_table.put(t.getClass(), tableName);
            }
        }
        return tableName;
    }

    public <T extends BaseEntity> boolean saveOrUpdate(List<T> tList) {
        if (CollectionUtil.isEmpty(tList)) {
            return false;
        }
        for (T t : tList) {
            saveOrUpdate(t);
        }

        return true;
    }

    public <T extends BaseEntity> boolean saveOrUpdate(T t) {
        if (t == null) {
            return false;
        }
        //T oldT = (T)entityManager.find(t.getClass(), t.getFid());
        entityManager.merge(t);

        return true;
    }

    protected OrderSpecifier getCreateOrder(SortEnum sortEnum, DateTimePath<Date> path) {
        if (sortEnum == null || sortEnum == SortEnum.DESC) {
            return new OrderSpecifier(Order.DESC, path);
        }
        return new OrderSpecifier(Order.ASC, path);
    }

}
