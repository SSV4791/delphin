package ru.ssv.delphin.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.controller.handler.ErrorResponse;

import javax.annotation.PostConstruct;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class BaseControllerTest {

    @LocalServerPort
    protected int port;

    protected static final String BASE_URI = "http://localhost:8088";

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

    protected Person insertTestedPerson() {
        var savedPerson = given()
                .baseUri(BASE_URI)
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .body(testedPerson)
                .post("/persons")
                .then()
                .statusCode(CREATED.value())
                .extract().as(PersonResponse.class)
                .getPerson();
        var personResponse = given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .get("/persons/{id}", savedPerson.getId())
                .then()
                .extract().as(PersonResponse.class);
        assertThat(personResponse)
                .isNotNull();
        return savedPerson;
    }

    protected void deletePersonById(String personId) {
        given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .delete("/persons/{id}", personId)
                .then()
                .statusCode(OK.value());
        var errorResponse = given()
                .baseUri(BASE_URI)
                .port(port)
                .when()
                .get("/persons/{id}", personId)
                .then()
                .extract().as(ErrorResponse.class);
        assertThat(errorResponse.getCode())
                .isEqualTo(NOT_FOUND.value());
    }
}