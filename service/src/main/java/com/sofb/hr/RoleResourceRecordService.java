package com.sofb.hr;

import com.sofb.BaseService;
import com.sofb.common.CollectionUtil;
import com.sofb.common.DateUtil;
import com.sofb.common.StringUtil;
import com.sofb.enums.StateEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class RoleResourceRecordService extends BaseService {

    public boolean removeByRoleId(Long roleId) {
        if (StringUtil.isEmpty(roleId)) {
            return false;
        }
        QRoleResourceRecord qRoleResourceRecord = QRoleResourceRecord.roleResourceRecord;
        jpaQueryFactory.update(qRoleResourceRecord).set(qRoleResourceRecord.state, StateEnum.DISABLED).
                set(qRoleResourceRecord.modifyDate, DateUtil.getCurrentDate()).
                where(qRoleResourceRecord.roleId.eq(roleId));

        return true;
    }

    @Transactional
    public boolean saveByRoleAndResourceIds(Long roleId, Set<Long> resourceList) {
        if (StringUtil.isEmpty(roleId) || CollectionUtil.isEmpty(resourceList)) {
            return false;
        }
        for (Long resourceId : resourceList) {
            if (resourceId == null) {
                continue;
            }
            RoleResourceRecord roleResourceRecord = new RoleResourceRecord();
            roleResourceRecord.setRoleId(roleId);
            roleResourceRecord.setResourceId(resourceId);
            insert(roleResourceRecord);
        }

        return true;
    }

}
