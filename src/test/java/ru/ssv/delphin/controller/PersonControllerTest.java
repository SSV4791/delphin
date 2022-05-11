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
    void getAllPersons() {
        var savedPerson = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when()
                .body(testedPerson)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract().as(PersonResponse.class)
                .getPerson();
        var response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons")
                .then()
                .statusCode(OK.value())
                .extract().as(PersonsResponse.class);
        assertThat(response.getPersons().contains(savedPerson))
                .isTrue();
    }

    @Test
    void getById() {
        var savedPerson = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when()
                .body(testedPerson)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract().as(PersonResponse.class)
                .getPerson();
        var response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons/{id}", savedPerson.getId())
                .then()
                .extract().as(PersonResponse.class);
        assertThat(response.getPerson())
                .isEqualTo(savedPerson);
    }

    @Test
    void getAllTasksByPersonId() {
        var savedPerson = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when()
                .body(testedPerson)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract().as(PersonResponse.class)
                .getPerson();
        var response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons/{id}/tasks", savedPerson.getId())
                .then()
                .extract().as(TasksResponse.class);
        assertThat(response.getTasks().containsAll(savedPerson.getTasks()))
                .isTrue();
    }

    @Test
    void savePerson() {
        var response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when()
                .body(testedPerson)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract().as(PersonResponse.class);
        assertThat(response.getPerson())
                .usingRecursiveComparison()
                .ignoringFields("id", "tasks")
                .isEqualTo(testedPerson);
    }
}