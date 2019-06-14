package com.sofb.voconvert;

import com.sofb.common.CollectionUtil;
import com.sofb.common.DateUtil;
import com.sofb.hr.Person;
import com.sofb.hr.Role;
import com.sofb.vo.PersonDetailVO;

import java.util.*;
import java.util.stream.Collectors;

public class PersonVOConvertUtil {
    public static PersonDetailVO p2v(Person person, String roleName) {
        if (person == null) {
            return null;
        }
        PersonDetailVO detailVO = new PersonDetailVO();
        detailVO.setId(person.getId());
        detailVO.setPhone(person.getPhone());
        detailVO.setUserName(person.getUserName());
        detailVO.setCreateDate(DateUtil.format(person.getCreateDate(), DateUtil.DATE_PATTERN));
        detailVO.setState(person.getState().getDesc());
        detailVO.setRoleName(roleName);
        return detailVO;
    }

    public static PersonDetailVO p2v(Person person) {
        return p2v(person, null);
    }

    public static List<PersonDetailVO> p2v(List<Person> personList) {
        return p2v(personList, null);
    }

    public static List<PersonDetailVO> p2v(List<Person> personList, Map<String, List<Role>> roleMap) {
        List<PersonDetailVO> voList = new ArrayList<>();
        if (CollectionUtil.isEmpty(personList)) {
            return voList;
        }
        if (roleMap == null) {
            roleMap = new HashMap<>();
        }
        for (Person person : personList) {
            if (person == null) {
                continue;
            }
            List<Role> roles = roleMap.get(person.getId());
            String roleName = null;
            if (!CollectionUtil.isEmpty(roles)) {
                Set<String> roleNames = roles.stream().map(item -> item.getRoleName()).collect(Collectors.toSet());
                roleName = String.join(",", roleNames);
            }
            voList.add(p2v(person, roleName));
        }

        return voList;
    }

}
