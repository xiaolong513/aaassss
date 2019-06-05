package com.sofb.hr;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sofb.BaseService;
import com.sofb.common.CollectionUtil;
import com.sofb.common.StringUtil;
import com.sofb.enums.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginPersonService extends BaseService {

    @Autowired
    private PersonService personService;

    @Autowired
    protected JPAQueryFactory jpaQueryFactory;

    public LoginPersonInfo getByName(String userName) {
        if (StringUtil.isEmpty(userName)) {
            return null;
        }
        Person person = personService.findByUserName(userName);
        if (person == null) {
            return null;
        }
        LoginPersonInfo loginPersonInfo = new LoginPersonInfo(person);

        List<Role> roles = listRolesByPersonId(person.getId());
        if (CollectionUtil.isEmpty(roles)) {
            return loginPersonInfo;
        }
        loginPersonInfo.setRoles(roles);

        List<Permission> permissions = listPersmissionsByPersonId(person.getId());
        if (CollectionUtil.isEmpty(permissions)) {
            return loginPersonInfo;
        }
        loginPersonInfo.setPermissions(permissions);

        return loginPersonInfo;
    }

    public List<Role> listRolesByPersonId(String personId) {
        List<Role> result = new ArrayList<>();
        if (StringUtil.isEmpty(personId)) {
            return result;
        }
        QPersonRoleRecord qPersonRoleRecord = QPersonRoleRecord.personRoleRecord;
        List<PersonRoleRecord> personRoleRecordList = jpaQueryFactory.selectFrom(qPersonRoleRecord).
                where(qPersonRoleRecord.personId.eq(personId), qPersonRoleRecord.state.eq(StateEnum.EFFECTIVE)).
                fetch();

        if (CollectionUtil.isEmpty(personRoleRecordList)) {
            return result;
        }
        Set<String> roleIds = personRoleRecordList.stream().map(item -> item.getRoleId()).collect(Collectors.toSet());

        if (CollectionUtil.isEmpty(roleIds)) {
            return result;
        }
        QRole qRole = QRole.role;
        result = jpaQueryFactory.selectFrom(qRole).where(qRole.id.in(roleIds), qRole.state.eq(StateEnum.EFFECTIVE)).fetch();
        /*result = jpaQueryFactory.selectFrom(qRole).
                where(qRole.id.in(JPAExpressions.select(qPersonRoleRecord.roleId).from(qPersonRoleRecord).where(qPersonRoleRecord.personId.eq(personId)))).fetch();*/

        return result;
    }

    public List<Permission> listPersmissionsByPersonId(String personId) {
        List<Permission> result = new ArrayList<>();
        if (StringUtil.isEmpty(personId)) {
            return result;
        }
        List<Role> roles = listRolesByPersonId(personId);
        if (CollectionUtil.isEmpty(roles)) {
            return result;
        }
        result = listPersmissionsByRoles(roles);

        return result;
    }

    public List<Permission> listPersmissionsByRoles(List<Role> roles) {
        List<Permission> result = new ArrayList<>();
        if (CollectionUtil.isEmpty(roles)) {
            return result;
        }

        Set<String> roleIds = roles.stream().map(item -> item.getId()).collect(Collectors.toSet());

        QRolePermissionRecord qRolePermissionRecord = QRolePermissionRecord.rolePermissionRecord;
        List<RolePermissionRecord> permissionRecords = jpaQueryFactory.selectFrom(qRolePermissionRecord).
                where(qRolePermissionRecord.roleId.in(roleIds), qRolePermissionRecord.state.eq(StateEnum.EFFECTIVE)).
                fetch();

        if (CollectionUtil.isEmpty(permissionRecords)) {
            return result;
        }
        Set<String> permissionIds = permissionRecords.stream().map(item -> item.getPermissionId()).collect(Collectors.toSet());
        QPermission qPermission = QPermission.permission1;
        result = jpaQueryFactory.selectFrom(qPermission).
                where(qPermission.id.in(permissionIds), qPermission.state.eq(StateEnum.EFFECTIVE)).
                fetch();

        return result;
    }
}
