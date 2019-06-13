package com.sofb.hr;

import com.sofb.common.StringUtil;
import com.sofb.enums.StateEnum;
import com.sofb.exceptions.PersonRoleRecordException;
import com.sofb.exceptions.RoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class RoleDealService {
    private static final Logger logger = LoggerFactory.getLogger("RoleDealService");

    @Autowired
    private PersonRoleRecordService personRoleRecordService;

    @Autowired
    private RoleResourceRecordService roleResourceRecordService;

    @Autowired
    private RoleService roleService;

    /**
     * 删除角色
     * 级联删除角色与人员的关联关系
     * 级联删除角色与资源的关联关系
     *
     * @return
     */
    @Transactional
    public boolean removeRoleById(Long roleId) {
        if (StringUtil.isEmpty(roleId)) {
            return false;
        }
        boolean result;

        //1 删除角色与人员的关联关系
        result = personRoleRecordService.removeByRoleId(roleId);
        if (!result) {
            throw new PersonRoleRecordException("删除人员的角色失败");
        }

        //2 删除角色与资源的关联关系
        result = roleResourceRecordService.removeByRoleId(roleId);
        if (!result) {
            throw new PersonRoleRecordException("删除角色的资源失败");
        }

        //3 删除角色
        result = roleService.removeById(roleId);
        if (!result) {
            throw new RoleException("删除角色失败");
        }

        return true;
    }

    public boolean addResource(Long id, String resourceIds) {
        if (StringUtil.isEmpty(id)) {
            logger.info("要添加资源的角色ID为空");
            return false;
        }
        if (StringUtil.isEmpty(resourceIds)) {
            logger.info("添加的资源ID为空");
            return false;
        }
        Role role = roleService.getById(id);
        if (role == null) {
            logger.info(String.format("不存在[%s]的角色", id));
            return false;
        }

        if (role.getState() == StateEnum.DISABLED) {
            logger.info(String.format("[%s]角色已被删除", id));
            return false;
        }
        Set<Long> resourceIdList = StringUtil.commaDelimitedListToLongSet(resourceIds);
        boolean result = roleResourceRecordService.saveByRoleAndResourceIds(id, resourceIdList);
        if (!result) {
            logger.info("为角色[%s]添加资源失败", id);
            return false;
        }

        return true;
    }
}
