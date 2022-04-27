package ru.ssv.delphin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

class PersonControllerTest extends BaseControllerTest{

    @Test
    void getAllPersons() {
        given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void getById() {
        var personId = "1";
        given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons/{id}", personId)
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

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