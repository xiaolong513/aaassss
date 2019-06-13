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
import com.sofb.exceptions.PersonException;
import com.sofb.form.hr.PersonSearchForm;
import com.sofb.shiro.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Optional optional = personRepository.findById(id);
        return (optional == null || !optional.isPresent()) ? null : (Person) optional.get();
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

    @Transactional
    public boolean savePerson(Person person) {
        boolean illegal = person == null || StringUtil.isEmpty(person.getUserName()) || StringUtil.isEmpty(person.getNumber()) ||
                StringUtil.isEmpty(person.getPhone());
        if (illegal) {
            return false;
        }

        //重复数据校验
        Person oldPerson = getByUserName(person.getUserName());
        if (oldPerson != null) {
            logger.info(String.format("已存在[%s]的用户", person.getUserName()));
            throw new PersonException(String.format("已存在[%s]的用户", person.getUserName()));
        }
        if (StringUtil.isEmpty(person.getId())) {
            person.setUUID();
        }
        //设置密码
        PasswordHelper.encryptPassword(person);

        insert(person);
        //((PersonService) AopContext.currentProxy()).insert(person);
        return true;

    }

    @Transactional
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

        long result = jpaQueryFactory.update(qPerson).
                set(qPerson.password, PasswordHelper.encryptPassword(person.getCredentialsSalt(), newPassword)).
                where(qPerson.id.eq(id)).execute();
        if (result < 0L) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean removeById(String id) {
        if (StringUtil.isEmpty(id)) {
            return false;
        }
        QPerson qPerson = QPerson.person;

        long result = jpaQueryFactory.update(qPerson).
                set(qPerson.state, StateEnum.DISABLED).
                set(qPerson.modifyDate, DateUtil.getCurrentDate()).
                //还需要设置操作人
                        where(qPerson.id.eq(id)).execute();
        if (result < 0L) {
            return false;
        }
        return true;
    }
}
