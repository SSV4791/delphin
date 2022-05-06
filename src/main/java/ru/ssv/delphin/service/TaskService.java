package ru.ssv.delphin.service;

import ru.ssv.delphin.api.model.TaskResponse;

public interface TaskService {
    TaskResponse getTaskById(String taskId);
}
