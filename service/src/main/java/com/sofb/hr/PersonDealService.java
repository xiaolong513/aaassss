package com.sofb.hr;

import com.sofb.common.StringUtil;
import com.sofb.enums.StateEnum;
import com.sofb.exceptions.PersonRoleRecordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class PersonDealService {
    private static final Logger logger = LoggerFactory.getLogger("PersonDealService");

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRoleRecordService personRoleRecordService;

    @Transactional
    public boolean removePersonById(String id) {
        if (StringUtil.isEmpty(id)) {
            return false;
        }
        Person person = personService.getById(id);
        if (person == null) {
            logger.info(String.format("不存在[%s]的经纪人", id));
            return false;
        }
        if (person.getState() == StateEnum.DISABLED) {
            logger.info(String.format("[%s]经纪人已被删除", id));
            return false;
        }
        boolean result = personRoleRecordService.removeByPersonId(id);
        if (!result) {
            throw new PersonRoleRecordException("删除经纪人角色失败");
        }
        result = personService.removeById(id);
        if (!result) {
            throw new PersonRoleRecordException("删除经纪人失败");
        }
        return true;
    }

    @Transactional
    public boolean addRole(String id, String roleIds) {
        if (StringUtil.isEmpty(id)) {
            logger.info("要添加角色的经纪人ID为空");
            return false;
        }
        if (StringUtil.isEmpty(roleIds)) {
            logger.info("为添加的角色ID为空");
            return false;
        }
        Person person = personService.getById(id);
        if (person == null) {
            logger.info(String.format("不存在[%s]的经纪人", id));
            return false;
        }

        if (person.getState() == StateEnum.DISABLED) {
            logger.info(String.format("[%s]经纪人已被删除", id));
            return false;
        }
        Set<Long> roleIdList = StringUtil.commaDelimitedListToLongSet(roleIds);
        boolean result = personRoleRecordService.saveByPersonAndRoleIds(person.getId(), roleIdList);
        if (!result) {
            logger.info("为经纪人[%s]添加角色失败", id);
            return false;
        }

        return true;
    }
}
