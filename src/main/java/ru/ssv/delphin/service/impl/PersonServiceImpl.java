package ru.ssv.delphin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;
import ru.ssv.delphin.db.repository.PersonRepository;
import ru.ssv.delphin.mapper.PersonMapper;
import ru.ssv.delphin.service.PersonService;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    @Transactional
    public PersonResponse create(PersonCreate createdPerson) {
        var personEntity = personMapper.toPersonEntity(createdPerson);
        var savedPersonEntity = personRepository.save(personEntity);
        var savedPerson = personMapper.toPerson(savedPersonEntity);
        return new PersonResponse().person(savedPerson);
    }

    @Override
    public PersonsResponse getAll() {
        var personEntities = personRepository.findAll();
        if (isEmpty(personEntities)) {
            throw new EntityNotFoundException("Пустой список пользователей системы");
        }
        var persons = personEntities.stream()
                .map(personMapper::toPerson)
                .collect(Collectors.toList());
        return new PersonsResponse().persons(persons);
    }

    @Override
    public TasksResponse getAllTasksByPersonId(String personId) {
        var personEntity = personRepository.getById(Long.valueOf(personId));
        var person = personMapper.toPerson(personEntity);
        if (isEmpty(person.getTasks())) {
            throw new EntityNotFoundException(format("Отсутствуют задачи для personId: %s", personId));
        }
        return new TasksResponse().tasks(person.getTasks());
    }

    @Override
    public PersonResponse getById(String personId) {
        var personEntity = personRepository.getById(Long.valueOf(personId));
        var person = personMapper.toPerson(personEntity);
        return new PersonResponse().person(person);
    }

    @Override
    @Transactional
    public void delete(String personId) {
        var targetPersonEntity =  personRepository.getById(Long.valueOf(personId));
        personRepository.delete(targetPersonEntity);
    }
}
