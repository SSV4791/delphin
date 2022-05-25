package ru.ssv.delphin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ssv.delphin.api.PersonsApi;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;
import ru.ssv.delphin.aspect.Validation;
import ru.ssv.delphin.service.PersonService;
import ru.ssv.delphin.validator.impl.CreatedPersonValidator;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonsApi {
    private final PersonService personService;

    @Override
    public ResponseEntity<PersonsResponse> getAllPersons() {
        return ResponseEntity
                .status(OK)
                .body(personService.getAllPersons());
    }

    @Override
    public ResponseEntity<TasksResponse> getAllTasksByPersonId(String personId) {
        return ResponseEntity
                .status(OK)
                .body(personService.getAllTasksByPersonId(personId));
    }

    @Override
    public ResponseEntity<PersonResponse> getById(String personId) {
        return ResponseEntity
                .status(OK)
                .body(personService.getById(personId));
    }

    @Override
    public ResponseEntity<PersonResponse> savePerson(@Validation(type = CreatedPersonValidator.class) Person person) {
        return ResponseEntity
                .status(CREATED)
                .body(personService.savePerson(person));
    }
}
