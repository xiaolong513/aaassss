package com.sofb.hr;

import com.alibaba.fastjson.JSON;
import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonRoleRecordServiceTest extends BaseTest {

    @Autowired
    private PersonRoleRecordService personRoleRecordService;

    @Test
    public void findByPersonId_test() {

        List<PersonRoleRecord> personRoleRecordList = personRoleRecordService.findByPersonId("");

        System.out.println("houseList：" + JSON.toJSONString(personRoleRecordList));
    }

}
