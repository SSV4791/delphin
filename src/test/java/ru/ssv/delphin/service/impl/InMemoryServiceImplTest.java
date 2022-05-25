package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.mapper.PersonMapper;
import ru.ssv.delphin.mapper.PersonMapperImpl;
import ru.ssv.delphin.mapper.TaskMapper;
import ru.ssv.delphin.mapper.TaskMapperImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryServiceImplTest extends AbstractServiceTest {
    private InMemoryBaseServiceImpl baseService;

    @BeforeEach
    void beforeEach() {
        TaskMapper taskMapper = new TaskMapperImpl();
        PersonMapper personMapper = new PersonMapperImpl(taskMapper);
        baseService = new InMemoryBaseServiceImpl(personMapper, taskMapper);
    }

    @Test
    void whenPutPerson_thenGetPerson() {
        var actualPerson = baseService.putPerson(createdPerson);
        assertThat(actualPerson)
                .usingRecursiveComparison()
                .ignoringFields("id", "tasks.id")
                .isEqualTo(expectedPerson);
    }

    @Test
    void getAllPersons() {
        var actualPerson = baseService.putPerson(createdPerson);
        List<Person> actualPersons = baseService.getAllPersons();
        assertThat(actualPersons.contains(actualPerson))
                .isTrue();
    }

    @Test
    void getAllTasksByPersonId() {
        var actualPerson = baseService.putPerson(createdPerson);
        List<Task> actualTasks = baseService.getAllTasksByPersonId(actualPerson.getId());
        assertThat(actualTasks.containsAll(actualPerson.getTasks()))
                .isTrue();
    }

    @Test
    void getPersonById() {
        var settingPerson = baseService.putPerson(createdPerson);
        var actualPerson = baseService.getPersonById(settingPerson.getId());
        assertThat(actualPerson)
                .usingRecursiveComparison()
                .ignoringFields("id", "tasks.id")
                .isEqualTo(expectedPerson);
    }

    @Test
    void getTaskById() {
        var settingPerson = baseService.putPerson(createdPerson);
        var actualTask = baseService.getTaskById(settingPerson.getTasks().get(0).getId());
        assertThat(actualTask)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedTask);
    }
}