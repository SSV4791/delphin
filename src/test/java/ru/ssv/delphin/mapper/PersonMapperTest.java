package ru.ssv.delphin.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ssv.delphin.db.entity.PersonEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class PersonMapperTest {

    private final static PodamFactory podamFactory = new PodamFactoryImpl();

    private final PersonMapper mapper;

    PersonMapperTest() {
        var taskMapper = new TaskMapperImpl();
        mapper = new PersonMapperImpl(taskMapper);
    }

    @Test
    void mapPersonEntityToPerson() {
        var personEntity = podamFactory.manufacturePojo(PersonEntity.class);
        var person = mapper.toPerson(personEntity);
        assertThat(person)
                .isNotNull();
        assertThat(person.getId())
                .isEqualTo(personEntity.getId().toString());
        assertThat(person.getName())
                .isEqualTo(personEntity.getName());
        assertThat(person.getTasks())
                .isNotEmpty();
        assertThat(person.getTasks().size())
                .isEqualTo(personEntity.getTasks().size());
    }
}