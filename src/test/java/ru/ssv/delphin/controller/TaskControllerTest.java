package ru.ssv.delphin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

class TaskControllerTest extends BaseControllerTest {

    @Test
    void getAllTasksByPersonId() {
        var personId = "1";
        given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons/{id}/tasks", personId)
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}