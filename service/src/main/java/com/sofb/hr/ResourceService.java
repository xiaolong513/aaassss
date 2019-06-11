package com.sofb.hr;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.sofb.BaseService;
import com.sofb.common.DateUtil;
import com.sofb.common.PageUtil;
import com.sofb.common.Pagination;
import com.sofb.common.StringUtil;
import com.sofb.enums.SortEnum;
import com.sofb.enums.StateEnum;
import com.sofb.form.hr.ResourceSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService extends BaseService {

    public boolean saveResource(Resource resource) {
        boolean illegal = resource == null || StringUtil.isEmpty(resource.getResourceName()) || StringUtil.isEmpty(resource.getResourceType());
        if (illegal) {
            return false;
        }

        insert(resource);

        return true;
    }

    public boolean removeById(Long id) {
        if (StringUtil.isEmpty(id)) {
            return false;
        }
        QResource qResource = QResource.resource;

        jpaQueryFactory.update(qResource).
                set(qResource.state, StateEnum.DISABLED).
                set(qResource.modifyDate, DateUtil.getCurrentDate()).
                //还需要设置操作人
                        where(qResource.id.eq(id));

        return true;
    }

    public List<Resource> listByResourceForm(ResourceSearchForm form, Pagination pagination, SortEnum sortEnum) {
        if (pagination == null) {
            return new ArrayList<>();
        }
        QResource qResource = QResource.resource;

        Predicate predicate = qResource.isNotNull().or(qResource.isNull());

        OrderSpecifier orderSpecifier = getCreateOrder(sortEnum, qResource.createDate);

        predicate = StringUtil.isEmpty(form.getResourceName()) ? predicate : ExpressionUtils.and(predicate, qResource.resourceName.like(form.getResourceName()));
        predicate = StringUtil.isEmpty(form.getUrl()) ? predicate : ExpressionUtils.and(predicate, qResource.url.eq(form.getUrl()));


        JPAQuery<Resource> jpaQuery = jpaQueryFactory.selectFrom(qResource).
                where(predicate).
                orderBy(orderSpecifier).
                limit(pagination.getPageSize()).offset(pagination.getOffSet());

        PageUtil.setPageData(pagination, jpaQuery);
        return pagination.getItems();
    }
}
