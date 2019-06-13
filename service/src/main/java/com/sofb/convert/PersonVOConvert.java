package com.sofb.convert;


import com.sofb.hr.Person;
import com.sofb.vo.PersonDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonVOConvert {
    PersonVOConvert INSTANCE = Mappers.getMapper(PersonVOConvert.class);

    @Mappings({
            @Mapping(source = "createDate", target = "createDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "state", expression = "java(person.getState().getDesc())")
    })
    PersonDetailVO p2v(Person person);

    List<PersonDetailVO> p2v(List<Person> personList);
}
