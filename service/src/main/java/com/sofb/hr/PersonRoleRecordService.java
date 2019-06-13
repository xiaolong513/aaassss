package com.sofb.hr;

import com.sofb.BaseService;
import com.sofb.common.CollectionUtil;
import com.sofb.common.DateUtil;
import com.sofb.common.StringUtil;
import com.sofb.enums.StateEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonRoleRecordService extends BaseService {
    public List<PersonRoleRecord> findByPersonId(String personId) {
        if (StringUtil.isEmpty(personId)) {
            return new ArrayList<>();
        }

        QPersonRoleRecord qPersonRoleRecord = QPersonRoleRecord.personRoleRecord;
        return jpaQueryFactory.selectFrom(qPersonRoleRecord).where(qPersonRoleRecord.personId.eq(personId),
                qPersonRoleRecord.state.eq(StateEnum.EFFECTIVE)).fetch();
    }


    public boolean removeByRoleId(Long roleId) {
        if (StringUtil.isEmpty(roleId)) {
            return false;
        }
        QPersonRoleRecord qPersonRoleRecord = QPersonRoleRecord.personRoleRecord;
        jpaQueryFactory.update(qPersonRoleRecord).set(qPersonRoleRecord.state, StateEnum.DISABLED).
                set(qPersonRoleRecord.modifyDate, DateUtil.getCurrentDate()).
                where(qPersonRoleRecord.roleId.eq(roleId), qPersonRoleRecord.state.eq(StateEnum.EFFECTIVE));

        return true;
    }

    public boolean removeByPersonId(String personId) {
        if (StringUtil.isEmpty(personId)) {
            return false;
        }
        QPersonRoleRecord qPersonRoleRecord = QPersonRoleRecord.personRoleRecord;
        jpaQueryFactory.update(qPersonRoleRecord).set(qPersonRoleRecord.state, StateEnum.DISABLED).
                set(qPersonRoleRecord.modifyDate, DateUtil.getCurrentDate()).
                where(qPersonRoleRecord.personId.eq(personId), qPersonRoleRecord.state.eq(StateEnum.EFFECTIVE));

        return true;
    }

    @Transactional
    public boolean saveByPersonAndRoleIds(String personId, Set<Long> roleIdList) {
        if (StringUtil.isEmpty(personId) || CollectionUtil.isEmpty(roleIdList)) {
            return false;
        }
        for (Long roleId : roleIdList) {
            if (roleId == null) {
                continue;
            }
            PersonRoleRecord personRoleRecord = new PersonRoleRecord();
            personRoleRecord.setPersonId(personId);
            personRoleRecord.setRoleId(roleId);
            insert(personRoleRecord);
        }

        return true;
    }
}
