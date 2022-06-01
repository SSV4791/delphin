package ru.ssv.delphin.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.api.model.TaskCreate;
import ru.ssv.delphin.db.entity.TaskEntity;

@Mapper(
        componentModel = "spring",
        uses = {PersonMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper {

    Task toTask(TaskEntity taskEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personEntity", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedOn", expression = "java(java.time.LocalDateTime.now())")
    TaskEntity toTaskEntity(TaskCreate taskCreate);
}
