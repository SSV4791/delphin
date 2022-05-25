package ru.ssv.delphin.mapper;

import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.api.model.TaskCreate;

import static org.assertj.core.api.Assertions.assertThat;

class TaskMapperTest extends BaseMapperTest{
    private final TaskMapper mapper = new TaskMapperImpl();

    @Test
    void toTask() {
        var createdTask = podamFactory.manufacturePojo(TaskCreate.class);
        var task = mapper.toTask(createdTask);
        assertThat(createdTask)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(mapper.toTaskCreate(task));
    }

    @Test
    void toTaskCreate() {
        var task = podamFactory.manufacturePojo(Task.class);
        var createdTask = mapper.toTaskCreate(task);
        assertThat(task)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFields("id")
                .isEqualTo(mapper.toTask(createdTask));
    }
}