package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryServiceImplTest extends AbstractServiceTest {
    private InMemoryBaseServiceImpl baseService;

    @BeforeEach
    void beforeEach() {
        baseService = new InMemoryBaseServiceImpl();
    }

    @Test
    void whenPutPerson_thenGetPerson() {
        var actualPerson = baseService.putPerson(testedPerson);
        assertThat(actualPerson)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(testedPerson);
    }

    @Test
    void getAllPersons() {
        var actualPerson = baseService.putPerson(testedPerson);
        List<Person> actualPersons = baseService.getAllPersons();
        assertThat(actualPersons.contains(actualPerson))
                .isTrue();
    }

    @Test
    void getAllTasksByPersonId() {
        var actualPerson = baseService.putPerson(testedPerson);
        List<Task> actualTasks = baseService.getAllTasksByPersonId(actualPerson.getId());
        assertThat(actualTasks.containsAll(testedPerson.getTasks()))
                .isTrue();
    }

    @Test
    void getPersonById() {
        var settingPerson = baseService.putPerson(testedPerson);
        var actualPerson = baseService.getPersonById(settingPerson.getId());
        assertThat(actualPerson)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(testedPerson);
    }

    @Test
    void getTaskById() {
        var settingPerson = baseService.putPerson(testedPerson);
        var actualTask = baseService.getTaskById(settingPerson.getTasks().get(0).getId());
        assertThat(actualTask)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(testedTask);
    }
}