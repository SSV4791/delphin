package ru.ssv.delphin.service.impl;

import lombok.RequiredArgsConstructor;
import ru.ssv.delphin.api.model.TaskResponse;
import ru.ssv.delphin.service.TaskService;

@RequiredArgsConstructor
public class InMemoryTaskServiceImpl implements TaskService {
    private final InMemoryBaseServiceImpl baseService;

    @Override
    public TaskResponse getTaskById(String taskId) {
        return new TaskResponse().task(baseService.getTaskById(taskId));
    }
}
