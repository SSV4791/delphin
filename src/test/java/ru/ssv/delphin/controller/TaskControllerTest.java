package ru.ssv.delphin.controller;

import org.junit.jupiter.api.Test;
import ru.ssv.delphin.controller.handler.ErrorResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

class TaskControllerTest extends BaseControllerTest {

    @Test
    void getAllTasksByPersonId() {
        var personId = "1";
        var response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons/{id}/tasks", personId)
                .then()
                .extract().as(ErrorResponse.class);
        assertThat(response.getCode())
                .isEqualTo(NOT_IMPLEMENTED.value());
    }
}