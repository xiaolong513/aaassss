package com.sofb.hr;

import com.sofb.BaseService;
import com.sofb.common.DateUtil;
import com.sofb.common.StringUtil;
import com.sofb.enums.StateEnum;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceRecordService extends BaseService {

    public boolean removeByRoleId(String roleId) {
        if (StringUtil.isEmpty(roleId)) {
            return false;
        }
        QRoleResourceRecord qRoleResourceRecord = QRoleResourceRecord.roleResourceRecord;
        jpaQueryFactory.update(qRoleResourceRecord).set(qRoleResourceRecord.state, StateEnum.DISABLED).
                set(qRoleResourceRecord.modifyDate, DateUtil.getCurrentDate()).
                where(qRoleResourceRecord.roleId.eq(roleId));

        return true;
    }

}
