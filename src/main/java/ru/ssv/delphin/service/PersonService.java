package ru.ssv.delphin.service;

import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;

public interface PersonService {
    PersonResponse savePerson(PersonCreate person);
    PersonsResponse getAllPersons();
    TasksResponse getAllTasksByPersonId(String personId);
    PersonResponse getById(String personId);
}
