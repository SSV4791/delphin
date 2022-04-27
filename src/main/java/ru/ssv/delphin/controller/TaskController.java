package ru.ssv.delphin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ssv.delphin.api.TasksApi;
import ru.ssv.delphin.api.model.task.TaskResponse;
import ru.ssv.delphin.exception.OperationNotSupportedException;

@RestController
public class TaskController implements TasksApi {

    @Override
    public ResponseEntity<TaskResponse> getTaskById(String taskId) {
        throw new OperationNotSupportedException("Операция не реализована");
    }
}
