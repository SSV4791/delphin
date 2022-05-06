package ru.ssv.delphin.service.impl;

import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.Task;
import ru.ssv.delphin.exception.DuplicatePersonException;
import ru.ssv.delphin.exception.EntityNotFoundedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class InMemoryBaseServiceImpl {
    private final Map<String, Person> persons = new ConcurrentHashMap<>();
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public Person putPerson(Person person) {
        requireNonNull(person);
        person.setId(getUUID());
        if (persons.get(person.getId()) != null) {
            throw new DuplicatePersonException(format("Дублирование Person c id:%s", person.getId()));
        }
        for (Task task: person.getTasks()) {
            task.setId(getUUID());
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

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}
