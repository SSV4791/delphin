package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.api.model.TaskResponse;
import ru.ssv.delphin.db.entity.TaskEntity;
import ru.ssv.delphin.db.repository.TaskRepository;
import ru.ssv.delphin.mapper.TaskMapper;
import ru.ssv.delphin.service.TaskService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {

    private final PodamFactory podamFactory = new PodamFactoryImpl();

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskMapper taskMapper;

    private TaskService taskService;

    @BeforeEach
    void beforeEach() {
        taskService = new TaskServiceImpl(taskRepository, taskMapper);
    }

    @Test
    void getTaskById() {
        var expectedTaskId = 1L;
        var expectedTaskEntity = podamFactory.manufacturePojo(TaskEntity.class);
        var expectedTask = podamFactory.manufacturePojo(Task.class);
        when(taskRepository.getById(eq(expectedTaskId)))
                .thenReturn(expectedTaskEntity);
        when(taskMapper.toTask(eq(expectedTaskEntity)))
                .thenReturn(expectedTask);
        var expectedTaskResponse = new TaskResponse()
                .task(expectedTask);
        var actualTaskResponse  = taskService.getTaskById(String.valueOf(expectedTaskId));
        assertThat(actualTaskResponse)
                .isEqualTo(expectedTaskResponse);
    }
}