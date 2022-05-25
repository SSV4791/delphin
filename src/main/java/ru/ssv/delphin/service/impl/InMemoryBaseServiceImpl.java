package ru.ssv.delphin.service.impl;

import lombok.RequiredArgsConstructor;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.exception.DuplicatePersonException;
import ru.ssv.delphin.exception.EntityNotFoundedException;
import ru.ssv.delphin.mapper.PersonMapper;
import ru.ssv.delphin.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RequiredArgsConstructor
public class InMemoryBaseServiceImpl {
    private final Map<String, Person> persons = new ConcurrentHashMap<>();
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();
    private final PersonMapper personMapper;
    private final TaskMapper taskMapper;

    public Person putPerson(PersonCreate createdPerson) {
        Person person = personMapper.toPerson(createdPerson);
        if (persons.get(person.getId()) != null) {
            throw new DuplicatePersonException(format("Дублирование Person c id:%s", person.getId()));
        }
        for (Task task: person.getTasks()) {
            tasks.put(task.getId(), task);
        }
        persons.put(person.getId(), person);
        return persons.get(person.getId());
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(persons.values());
    }

    public List<Task> getAllTasksByPersonId(String personId) {
        return persons.entrySet().stream()
                .filter(entry -> personId.equals(entry.getKey()))
                .map(entry -> entry.getValue().getTasks())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Person getPersonById(String personId) {
        return persons.entrySet().stream()
                .filter(entry -> personId.equals(entry.getKey()))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow(() -> new EntityNotFoundedException(format("Не найден пользователь с id: %s", personId)));
    }

    public Task getTaskById(String taskId) {
        return tasks.entrySet().stream()
                .filter(entry -> taskId.equals(entry.getKey()))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow(() -> new EntityNotFoundedException(format("Не найдена задача с id: %s", taskId)));
    }
}
