package ru.ssv.delphin.service.impl;

import lombok.RequiredArgsConstructor;
import ru.ssv.delphin.api.model.TaskResponse;
import ru.ssv.delphin.db.repository.TaskRepository;
import ru.ssv.delphin.mapper.TaskMapper;
import ru.ssv.delphin.service.TaskService;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse getTaskById(String taskId) {
        var taskEntity = taskRepository.getById(Long.valueOf(taskId));
        var task = taskMapper.toTask(taskEntity);
        return new TaskResponse().task(task);
    }
}
