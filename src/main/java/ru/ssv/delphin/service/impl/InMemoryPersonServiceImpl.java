package ru.ssv.delphin.service.impl;

import lombok.RequiredArgsConstructor;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;
import ru.ssv.delphin.service.PersonService;

@RequiredArgsConstructor
public class InMemoryPersonServiceImpl implements PersonService {
    private final InMemoryBaseServiceImpl baseService;

    @Override
    public PersonResponse savePerson(Person person) {
        return new PersonResponse()
                .person(baseService.putPerson(person));
    }

    @Override
    public PersonsResponse getAllPersons() {
        return new PersonsResponse()
                .persons(baseService.getAllPersons());
    }

    @Override
    public TasksResponse getAllTasksByPersonId(String personId) {
        return new TasksResponse()
                .personId(personId)
                .tasks(baseService.getAllTasksByPersonId(personId));
    }

    @Override
    public PersonResponse getById(String personId) {
        return new PersonResponse()
                .person(baseService.getPersonById(personId));
    }
}
