package ru.ssv.delphin.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

class PersonControllerTest extends BaseControllerTest {

    @Test
    void getAll() {
        var testedPerson = insertTestedPerson();
        var response = given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .get("/persons")
                .then()
                .statusCode(OK.value())
                .extract().as(PersonsResponse.class);
        assertThat(response.getPersons().contains(testedPerson))
                .isTrue();
        deletePersonById(testedPerson.getId());
    }

    @Test
    void getById() {
        var testedPerson = insertTestedPerson();;
        var personResponse = given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .get("/persons/{id}", testedPerson.getId())
                .then()
                .extract().as(PersonResponse.class);
        assertThat(personResponse.getPerson())
                .isEqualTo(testedPerson);
        deletePersonById(testedPerson.getId());
    }

    @Test
    void getAllTasksByPersonId() {
        var testedPerson = insertTestedPerson();
        var response = given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .get("/persons/{id}/tasks", testedPerson.getId())
                .then()
                .extract().as(TasksResponse.class);
        assertThat(response.getTasks().containsAll(testedPerson.getTasks()))
                .isTrue();
        deletePersonById(testedPerson.getId());
    }

    @Test
    void create() {
        var createdPersonResponse = given()
                .baseUri(BASE_URI)
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .body(testedPerson)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract().as(PersonResponse.class);
        assertThat(createdPersonResponse.getPerson())
                .usingRecursiveComparison()
                .ignoringFields("id", "tasks")
                .isEqualTo(testedPerson);
        deletePersonById(createdPersonResponse.getPerson().getId());
    }
}