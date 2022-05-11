package ru.ssv.delphin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ssv.delphin.api.TasksApi;
import ru.ssv.delphin.api.model.TaskResponse;
import ru.ssv.delphin.service.TaskService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskResponse> getTaskById(String taskId) {
        return ResponseEntity
                .status(OK)
                .body(taskService.getTaskById(taskId));
    }
}
