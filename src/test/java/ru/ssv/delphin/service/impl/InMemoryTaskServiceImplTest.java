package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.Task;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InMemoryTaskServiceImplTest extends AbstractBaseServiceTest {
    private InMemoryTaskServiceImpl taskService;
    private InMemoryBaseServiceImpl baseService;

    @BeforeEach
    void beforeEach() {
        baseService = mock(InMemoryBaseServiceImpl.class);
        taskService = new InMemoryTaskServiceImpl(baseService);
    }

    @Test
    void getTaskById() {
        var taskId = UUID.randomUUID().toString();
        when(baseService.getTaskById(taskId))
                .thenReturn(testedTask);
        var actualTaskResponse = taskService.getTaskById(taskId);
        assertThat(actualTaskResponse.getTask())
                .isEqualTo(testedTask);
    }
}