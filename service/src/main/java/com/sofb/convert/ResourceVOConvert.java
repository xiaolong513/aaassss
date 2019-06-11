package com.sofb.convert;

import com.sofb.hr.Resource;
import com.sofb.vo.ResourceDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceVOConvert {
    ResourceVOConvert INSTANCE = Mappers.getMapper(ResourceVOConvert.class);

    @Mappings({
            @Mapping(source = "createDate", target = "createDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "resourceName", target = "resourceName"),
            @Mapping(source = "resourceType", target = "resourceType"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "permission", target = "permission"),
            @Mapping(source = "description", target = "description")

    })
    ResourceDetailVO c2h(Resource resource);

    List<ResourceDetailVO> c2h(List<Resource> resources);
}
