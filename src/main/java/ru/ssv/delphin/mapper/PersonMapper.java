package ru.ssv.delphin.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonCreate;

@Mapper(
        componentModel = "spring",
        uses = {TaskMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Person toPerson(PersonCreate createdPerson);

    PersonCreate toPersonCreate(Person person);
}
