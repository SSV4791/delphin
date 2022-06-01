package ru.ssv.delphin.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.db.entity.PersonEntity;

import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper(
        componentModel = "spring",
        uses = {TaskMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMapper {

    Person toPerson(PersonEntity personEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedOn", expression = "java(java.time.LocalDateTime.now())")
    PersonEntity toPersonEntity(PersonCreate createdPerson);

    @AfterMapping
    default void updatePersonEntity(@MappingTarget PersonEntity personEntity) {
        if (isNull(personEntity)) {
            return;
        }
        if (!isEmpty(personEntity.getTasks())) {
            personEntity.getTasks().forEach(taskEntity -> taskEntity.setPersonEntity(personEntity));
        }
    }
}
