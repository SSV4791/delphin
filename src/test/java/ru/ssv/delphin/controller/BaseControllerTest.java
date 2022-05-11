package ru.ssv.delphin.controller;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.Task;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class BaseControllerTest {
    protected static final String BASE_URI = "http://localhost";

    protected Person testedPerson;

    @PostConstruct
    void init() {
        var savedTask1 = new Task()
                .name("task-name-1");
        var savedTask2 = new Task()
                .name("task-name-2");
        testedPerson = new Person()
                .name("person-name")
                .tasks(List.of(savedTask1, savedTask2));
    }
}