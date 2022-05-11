package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.Task;

import java.util.List;

public abstract class AbstractServiceTest {
    protected Task testedTask;
    protected Person testedPerson;

    @BeforeEach
    void beforeAll() {
        String TASK_NAME_FOR_TESTED_PERSON = "TaskName for tested person";
        String PERSON_NAME_FOR_TESTED_PERSON = "PersonName for tested person";
        testedTask = new Task()
                .name(TASK_NAME_FOR_TESTED_PERSON);
        testedPerson = new Person()
                .name(PERSON_NAME_FOR_TESTED_PERSON)
                .tasks(List.of(testedTask));
    }
}
