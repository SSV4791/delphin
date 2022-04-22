package ru.ssv.delphin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ssv.delphin.api.person.PersonsApi;
import ru.ssv.delphin.api.person.model.Person;
import ru.ssv.delphin.api.person.model.PersonResponse;
import ru.ssv.delphin.api.person.model.PersonsResponse;

import java.util.List;

@RestController
public class PersonController implements PersonsApi {
    @Override
    public ResponseEntity<PersonsResponse> getAllPersons() {
        var person = new Person().id("id").name("name");
        var personsResponse = new PersonsResponse().persons(List.of(person));
        return ResponseEntity.status(HttpStatus.OK).body(personsResponse);
    }

    @Override
    public ResponseEntity<PersonResponse> getById(String personId) {
        var person = new Person().id("id").name("name");
        var personsResponse = new PersonResponse().person(person);
        return ResponseEntity.status(HttpStatus.OK).body(personsResponse);
    }
}
