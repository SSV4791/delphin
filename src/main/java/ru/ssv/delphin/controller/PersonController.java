package ru.ssv.delphin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ssv.delphin.api.PersonsApi;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;
import ru.ssv.delphin.aspect.Validation;
import ru.ssv.delphin.service.PersonService;
import ru.ssv.delphin.validator.impl.CreatedPersonValidatorImpl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonsApi {
    private final PersonService personService;

    @Override
    public ResponseEntity<PersonsResponse> getAll() {
        return ResponseEntity
                .status(OK)
                .body(personService.getAll());
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
    public ResponseEntity<PersonResponse> create(@Validation(type = CreatedPersonValidatorImpl.class) PersonCreate person) {
        return ResponseEntity
                .status(CREATED)
                .body(personService.create(person));
    }

    @Override
    public ResponseEntity<Void> delete(String personId) {
        personService.delete(personId);
        return ResponseEntity.status(OK).build();
    }
}
