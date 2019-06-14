package com.sofb.hr;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sofb.BaseService;
import com.sofb.common.CollectionUtil;
import com.sofb.common.SessionPerson;
import com.sofb.common.StringUtil;
import com.sofb.enums.BooleanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginPersonService extends BaseService {

    @Autowired
    private PersonService personService;

    @Autowired
    protected JPAQueryFactory jpaQueryFactory;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    public LoginPersonInfo getByCurrentPerson() {
        Person currentPerson = SessionPerson.get();
        if (currentPerson == null) {
            return null;
        }
        return getByName(currentPerson.getUserName());
    }

    public LoginPersonInfo getByName(String userName) {
        if (StringUtil.isEmpty(userName)) {
            return null;
        }
        Person person = personService.getByUserName(userName);
        if (person == null) {
            return null;
        }
        LoginPersonInfo loginPersonInfo = new LoginPersonInfo(person);

        List<Role> roles = roleService.listRolesByPersonId(person.getId());
        if (CollectionUtil.isEmpty(roles)) {
            return loginPersonInfo;
        }
        loginPersonInfo.setRoles(roles);

        List<Resource> resources = listResourcesByPersonId(person.getId());
        if (CollectionUtil.isEmpty(resources)) {
            return loginPersonInfo;
        }
        loginPersonInfo.setResources(resources);

        return loginPersonInfo;
    }


    public List<Resource> listResourcesByPersonId(String personId) {
        List<Resource> result = new ArrayList<>();
        if (StringUtil.isEmpty(personId)) {
            return result;
        }
        List<Role> roles = roleService.listRolesByPersonId(personId);
        if (CollectionUtil.isEmpty(roles)) {
            return result;
        }
        result = resourceService.listResourceByRoles(roles, BooleanEnum.TURE);

        return result;
    }


}
