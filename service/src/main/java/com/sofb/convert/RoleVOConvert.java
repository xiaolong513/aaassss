package com.sofb.convert;

import com.sofb.hr.Role;
import com.sofb.vo.RoleDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleVOConvert {
    RoleVOConvert INSTANCE = Mappers.getMapper(RoleVOConvert.class);

    @Mappings({
            @Mapping(source = "createDate", target = "createDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "roleName", target = "roleName"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "state", expression = "java(role.getState().getDesc())")

    })
    RoleDetailVO c2h(Role role);

    List<RoleDetailVO> c2h(List<Role> personList);
}
