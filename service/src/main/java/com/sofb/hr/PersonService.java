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
import com.sofb.form.hr.PersonSearchForm;
import com.sofb.shiro.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger("PersonService");

    @Autowired
    private PersonRepository personRepository;

    public Person getByUserName(String userName) {
        if (StringUtil.isEmpty(userName)) {
            return null;
        }

        return personRepository.findByUserName(userName);
    }

    public Person getById(String id) {
        if (StringUtil.isEmpty(id)) {
            return null;
        }
        return personRepository.findById(id).get();
    }

    public List<Person> listByPersonForm(PersonSearchForm form, Pagination page, SortEnum sortEnum) {
        if (page == null) {
            return new ArrayList<>();
        }
        QPerson qPerson = QPerson.person;

        Predicate predicate = qPerson.isNotNull().or(qPerson.isNull());

        OrderSpecifier orderSpecifier = getCreateOrder(sortEnum, qPerson.createDate);

        predicate = StringUtil.isEmpty(form.getUserName()) ? predicate : ExpressionUtils.and(predicate, qPerson.userName.eq(form.getUserName()));
        predicate = StringUtil.isEmpty(form.getPhone()) ? predicate : ExpressionUtils.and(predicate, qPerson.phone.eq(form.getPhone()));


        JPAQuery<Person> jpaQuery = jpaQueryFactory.selectFrom(qPerson).
                where(predicate).
                orderBy(orderSpecifier).
                limit(page.getPageSize()).offset(page.getOffSet());

        PageUtil.setPageData(page, jpaQuery);
        return page.getItems();
    }

    public boolean createPerson(Person person) {
        boolean illegal = person == null || StringUtil.isEmpty(person.getUserName()) || StringUtil.isEmpty(person.getNumber());
        if (illegal) {
            return false;
        }
        //设置密码
        PasswordHelper.encryptPassword(person);

        insert(person);

        return true;
    }

    public boolean changePassword(String id, String newPassword) {
        boolean illegal = StringUtil.isEmpty(id) || StringUtil.isEmpty(newPassword);
        if (illegal) {
            return false;
        }
        Person person = getById(id);
        if (person == null) {
            logger.info("用户不存在");
            return false;
        }
        QPerson qPerson = QPerson.person;

        jpaQueryFactory.update(qPerson).
                set(qPerson.password, PasswordHelper.encryptPassword(person.getCredentialsSalt(), newPassword)).
                where(qPerson.id.eq(id));

        return true;
    }

    public boolean removeById(String id) {
        if (StringUtil.isEmpty(id)) {
            return false;
        }
        QPerson qPerson = QPerson.person;

        jpaQueryFactory.update(qPerson).
                set(qPerson.state, StateEnum.DISABLED).
                set(qPerson.modifyDate, DateUtil.getCurrentDate()).
                //还需要设置操作人
                        where(qPerson.id.eq(id));

        return true;
    }
}
