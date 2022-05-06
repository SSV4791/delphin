package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InMemoryPersonServiceImplTest extends AbstractBaseServiceTest {
    private InMemoryPersonServiceImpl personService;
    private InMemoryBaseServiceImpl baseService;

    @BeforeEach
    void beforeEach() {
        baseService = mock(InMemoryBaseServiceImpl.class);
        personService = new InMemoryPersonServiceImpl(baseService);
    }

    @Test
    void getAllPersons() {
        var expectedPersons = List.of(testedPerson);
        when(baseService.getAllPersons())
                .thenReturn(expectedPersons);
        var actualPersonsResponse = personService.getAllPersons();
        assertThat(actualPersonsResponse.getPersons())
                .isEqualTo(expectedPersons);
    }

    @Test
    void getAllTasksByPersonId() {
        var personId = UUID.randomUUID().toString();
        var expectedTasks = List.of(testedTask);
        when(baseService.getAllTasksByPersonId(personId))
                .thenReturn(expectedTasks);
        var actualTasksResponse = personService.getAllTasksByPersonId(personId);
        assertThat(actualTasksResponse.getTasks())
                .isEqualTo(expectedTasks);
    }

    @Test
    void getById() {
        var personId = UUID.randomUUID().toString();
        when(baseService.getPersonById(personId))
                .thenReturn(testedPerson);
        var actualPersonResponse = personService.getById(personId);
        assertThat(actualPersonResponse.getPerson())
                .isEqualTo(testedPerson);
    }
}