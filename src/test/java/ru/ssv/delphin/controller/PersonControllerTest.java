package ru.ssv.delphin.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.api.person.model.Person;
import ru.ssv.delphin.api.person.model.PersonResponse;
import ru.ssv.delphin.api.person.model.PersonsResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    private static final String BASE_URI = "http://localhost";

    @Test
    void getAllPersons() {
        var expectedPerson = new Person().id("id").name("name");
        var response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(PersonsResponse.class);
        assertThat(response.getPersons().size())
                .isEqualTo(1);
        assertThat(response.getPersons().get(0))
                .isEqualTo(expectedPerson);
    }

    @Test
    void getById() {
        var personId = "1";
        var expectedPerson = new Person().id(personId).name("name");
        var response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/persons/{id}", personId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(PersonResponse.class);
        assertThat(response.getPerson())
                .isEqualTo(expectedPerson);
    }
}