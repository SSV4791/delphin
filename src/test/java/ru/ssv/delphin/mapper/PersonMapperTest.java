package ru.ssv.delphin.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.api.model.PersonCreate;

import static org.assertj.core.api.Assertions.assertThat;

class PersonMapperTest extends BaseMapperTest{
    private static PersonMapper mapper;

    @BeforeAll
    static void beforeAll() {
        TaskMapper taskMapper = new TaskMapperImpl();
        mapper = new PersonMapperImpl(taskMapper);
    }

    @Test
    void toPerson() {
        var createdPerson = podamFactory.manufacturePojo(PersonCreate.class);
        var person = mapper.toPerson(createdPerson);
        assertThat(createdPerson)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(mapper.toPersonCreate(person));
    }

    @Test
    void toPersonCreate() {
        var person = podamFactory.manufacturePojo(Person.class);
        var createdPerson = mapper.toPersonCreate(person);
        assertThat(person)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFields("id", "tasks.id")
                .isEqualTo(mapper.toPerson(createdPerson));
    }
}