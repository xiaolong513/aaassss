package com.sofb.hr;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.sofb.BaseService;
import com.sofb.common.*;
import com.sofb.enums.SortEnum;
import com.sofb.enums.StateEnum;
import com.sofb.form.hr.RoleSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger("RoleService");
    @Autowired
    private RoleRepository roleRepository;

    public boolean saveRole(Role role) {
        if (role == null || StringUtil.isEmpty(role.getRoleName())) {
            return false;
        }
        return saveOrUpdate(role);
    }

    public Role getById(Long id) {
        if (StringUtil.isEmpty(id)) {
            return null;
        }
        Optional<Role> optional = roleRepository.findById(id);
        return (optional == null || !optional.isPresent()) ? null : optional.get();
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

    public boolean removeById(Long id) {
        if (StringUtil.isEmpty(id)) {
            return false;
        }
        QRole qRole = QRole.role;

        jpaQueryFactory.update(qRole).
                set(qRole.state, StateEnum.DISABLED).
                set(qRole.modifyDate, DateUtil.getCurrentDate()).
                //还需要设置操作人
                        where(qRole.id.eq(id)).execute();

        return true;
    }
}
