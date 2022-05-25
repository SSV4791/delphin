package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.api.model.TaskCreate;

import java.util.List;

public abstract class AbstractServiceTest {
    protected TaskCreate createdTask;
    protected PersonCreate createdPerson;
    protected Task expectedTask;
    protected Person expectedPerson;

    @BeforeEach
    void beforeAll() {
        String TASK_NAME_FOR_TESTED_PERSON = "TaskName for tested person";
        String PERSON_NAME_FOR_TESTED_PERSON = "PersonName for tested person";
        createdTask = new TaskCreate()
                .name(TASK_NAME_FOR_TESTED_PERSON);
        createdPerson = new PersonCreate()
                .name(PERSON_NAME_FOR_TESTED_PERSON)
                .tasks(List.of(createdTask));
        expectedTask = new Task()
                .name(TASK_NAME_FOR_TESTED_PERSON);
        expectedPerson = new Person()
                .name(PERSON_NAME_FOR_TESTED_PERSON)
                .tasks(List.of(expectedTask));
    }
}
