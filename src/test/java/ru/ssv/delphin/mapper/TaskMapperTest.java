package ru.ssv.delphin.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.db.entity.TaskEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class TaskMapperTest {

    private static final PodamFactory podamFactory = new PodamFactoryImpl();

    private static final TaskMapper mapper = new TaskMapperImpl();

    @Test
    void mapTaskEntityToTask() {
        var taskEntity = podamFactory.manufacturePojo(TaskEntity.class);
        var task = mapper.toTask(taskEntity);
        assertThat(task)
                .isNotNull();
        assertThat(task.getId())
                .isEqualTo(taskEntity.getId().toString());
        assertThat(task.getName())
                .isEqualTo(taskEntity.getName());
    }

}