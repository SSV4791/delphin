package ru.ssv.delphin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.api.model.PersonResponse;
import ru.ssv.delphin.api.model.PersonsResponse;
import ru.ssv.delphin.api.model.TasksResponse;
import ru.ssv.delphin.db.entity.PersonEntity;
import ru.ssv.delphin.db.repository.PersonRepository;
import ru.ssv.delphin.mapper.PersonMapper;
import ru.ssv.delphin.mapper.TaskMapper;
import ru.ssv.delphin.service.PersonService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonServiceImplTest {

    private PodamFactory podamFactory = new PodamFactoryImpl();

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private PersonMapper personMapper;

    @MockBean
    private TaskMapper taskMapper;

    private PersonService personService;

    @BeforeEach
    void beforeEach() {
        personService = new PersonServiceImpl(personRepository, personMapper);
    }

    @Test
    void getById() {
        var expectedPersonId = 1L;
        var expectedPersonEntity = podamFactory.manufacturePojo(PersonEntity.class);
        var expectedPerson = podamFactory.manufacturePojo(Person.class);
        when(personRepository.getById(eq(expectedPersonId)))
                .thenReturn(expectedPersonEntity);
        when(personMapper.toPerson(eq(expectedPersonEntity)))
                .thenReturn(expectedPerson);
        var expectedPersonResponse = new PersonResponse()
                .person(expectedPerson);
        var actualPersonResponse = personService.getById(String.valueOf(expectedPersonId));
        assertThat(actualPersonResponse)
                .isEqualTo(expectedPersonResponse);
    }

    @Test
    void getAllPersons() {
        List<PersonEntity> expectedPersonEntities = List.of(podamFactory.manufacturePojo(PersonEntity.class));
        List<Person> expectedPersons = new ArrayList<>();
        when(personRepository.findAll())
                .thenReturn(expectedPersonEntities);
        expectedPersonEntities.forEach(personEntity -> {
            var expectedPerson = podamFactory.manufacturePojo(Person.class);
            when(personMapper.toPerson(eq(personEntity)))
                    .thenReturn(expectedPerson);
            expectedPersons.add(expectedPerson);
        });
        var expectedPersonsResponse = new PersonsResponse()
                .persons(expectedPersons);
        var actualPersonsResponse = personService.getAll();
        assertThat(actualPersonsResponse)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedPersonsResponse);
    }

    @Test
    void getAllTasksByPersonId() {
        var expectedPersonId = 1L;
        var expectedPersonEntity = podamFactory.manufacturePojo(PersonEntity.class);
        var expectedPerson = podamFactory.manufacturePojo(Person.class);
        when(personRepository.getById(eq(expectedPersonId)))
                .thenReturn(expectedPersonEntity);
        when(personMapper.toPerson(eq(expectedPersonEntity)))
                .thenReturn(expectedPerson);
        var expectedTasksResponse = new TasksResponse()
                .tasks(expectedPerson.getTasks());
        var actualTasksResponse = personService.getAllTasksByPersonId(String.valueOf(expectedPersonId));
        assertThat(actualTasksResponse)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedTasksResponse);
    }

    @Test
    void savePerson() {
        var personCreate = podamFactory.manufacturePojo(PersonCreate.class);
        var savingPersonEntity = podamFactory.manufacturePojo(PersonEntity.class);
        when(personMapper.toPersonEntity(eq(personCreate)))
                .thenReturn(savingPersonEntity);
        var savedPersonEntity = podamFactory.manufacturePojo(PersonEntity.class);
        when(personRepository.save(eq(savingPersonEntity)))
                .thenReturn(savedPersonEntity);
        var expectedSavedPerson = podamFactory.manufacturePojo(Person.class);
        when(personMapper.toPerson(eq(savedPersonEntity)))
                .thenReturn(expectedSavedPerson);
        var expectedPersonResponse = new PersonResponse()
                .person(expectedSavedPerson);
        var actualPersonResponse = personService.create(personCreate);
        assertThat(actualPersonResponse)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedPersonResponse);
    }
}