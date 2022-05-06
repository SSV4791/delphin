package ru.ssv.delphin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ssv.delphin.api.PersonsApi;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;
import ru.ssv.delphin.exception.OperationNotSupportedException;

@RestController
public class PersonController implements PersonsApi {
    @Override
    public ResponseEntity<PersonsResponse> getAllPersons() {
        throw new OperationNotSupportedException("Операция не реализована");
    }

    @Override
    public ResponseEntity<TasksResponse> getAllTasksByPersonId(String personId) {
        throw new OperationNotSupportedException("Операция не реализована");
    }

    @Override
    public ResponseEntity<PersonResponse> getById(String personId) {
        throw new OperationNotSupportedException("Операция не реализована");
    }
}
