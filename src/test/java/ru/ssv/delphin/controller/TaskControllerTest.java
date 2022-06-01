package ru.ssv.delphin.controller;

import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.TasksResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class TaskControllerTest extends BaseControllerTest {

    @Test
    void getAllTasksByPersonId() {
        var testedPerson = insertTestedPerson();
        var tasksResponse = given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .get("/persons/{id}/tasks", testedPerson.getId())
                .then()
                .extract().as(TasksResponse.class);
        assertThat(tasksResponse.getTasks().containsAll(testedPerson.getTasks()))
                .isTrue();
        deletePersonById(testedPerson.getId());
    }
}