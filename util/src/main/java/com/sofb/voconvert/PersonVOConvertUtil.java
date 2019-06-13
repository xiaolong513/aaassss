package com.sofb.voconvert;

import com.sofb.common.CollectionUtil;
import com.sofb.common.DateUtil;
import com.sofb.hr.Person;
import com.sofb.vo.PersonDetailVO;

import java.util.ArrayList;
import java.util.List;

public class PersonVOConvertUtil {
    public static PersonDetailVO p2v(Person person) {
        if (person == null) {
            return null;
        }
        PersonDetailVO detailVO = new PersonDetailVO();
        detailVO.setId(person.getId());
        detailVO.setPhone(person.getPhone());
        detailVO.setUserName(person.getUserName());
        detailVO.setCreateDate(DateUtil.format(person.getCreateDate(), DateUtil.DATE_PATTERN));
        detailVO.setState(person.getState().getDesc());
        detailVO.setRoleName("");
        return detailVO;
    }

    public static List<PersonDetailVO> p2v(List<Person> personList) {
        List<PersonDetailVO> voList = new ArrayList<>();
        if (CollectionUtil.isEmpty(personList)) {
            return voList;
        }
        for (Person person : personList) {
            if (person == null) {
                continue;
            }
            voList.add(p2v(person));
        }

        return voList;
    }

}
