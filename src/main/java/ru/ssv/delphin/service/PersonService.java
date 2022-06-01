package ru.ssv.delphin.service;

import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;

public interface PersonService {
    PersonResponse create(PersonCreate person);
    PersonsResponse getAll();
    TasksResponse getAllTasksByPersonId(String personId);
    PersonResponse getById(String personId);
    void delete(String personId);
}
