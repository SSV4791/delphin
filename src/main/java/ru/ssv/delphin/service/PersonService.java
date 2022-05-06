package ru.ssv.delphin.service;

import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;

public interface PersonService {
    PersonsResponse getAllPersons();
    TasksResponse getAllTasksByPersonId(String personId);
    PersonResponse getById(String personId);
}
