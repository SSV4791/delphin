package ru.ssv.delphin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.api.model.TaskCreate;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Task toTask(TaskCreate createdTask);
}
