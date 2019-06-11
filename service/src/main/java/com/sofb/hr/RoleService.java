package com.sofb.hr;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
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
import com.sofb.form.hr.RoleSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleRepository roleRepository;

    public boolean saveRole(Role role) {
        if (role == null || StringUtil.isEmpty(role.getRoleName())) {
            return false;
        }
        return saveOrUpdate(role);
    }

    public Role getById(String id) {
        if (StringUtil.isEmpty(id)) {
            return null;
        }
        return roleRepository.findById(id).get();
    }

    public List<Role> listByRoleForm(RoleSearchForm form, Pagination page, SortEnum sortEnum) {
        if (page == null) {
            return new ArrayList<>();
        }
        QRole qRole = QRole.role;

        Predicate predicate = qRole.isNotNull().or(qRole.isNull());

        OrderSpecifier orderSpecifier = new OrderSpecifier(Order.DESC, qRole.createDate);
        if (sortEnum == SortEnum.ASC) {
            orderSpecifier = new OrderSpecifier(Order.ASC, qRole.createDate);
        }

        predicate = StringUtil.isEmpty(form.getRoleName()) ? predicate : ExpressionUtils.and(predicate, qRole.roleName.eq(form.getRoleName()));
        predicate = StringUtil.isEmpty(form.getDescription()) ? predicate : ExpressionUtils.and(predicate, qRole.description.eq(form.getDescription()));


        JPAQuery<Role> jpaQuery = jpaQueryFactory.selectFrom(qRole).
                where(predicate).
                orderBy(orderSpecifier).
                limit(page.getPageSize()).
                offset(page.getOffSet());

        PageUtil.setPageData(page, jpaQuery);
        return page.getItems();
    }

    public boolean removeById(String id) {
        if (StringUtil.isEmpty(id)) {
            return false;
        }
        QRole qRole = QRole.role;

        jpaQueryFactory.update(qRole).
                set(qRole.state, StateEnum.DISABLED).
                set(qRole.modifyDate, DateUtil.getCurrentDate()).
                //还需要设置操作人
                        where(qRole.id.eq(id));

        return true;
    }
}
