package ru.ssv.delphin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ssv.delphin.db.repository.PersonRepository;
import ru.ssv.delphin.db.repository.TaskRepository;
import ru.ssv.delphin.mapper.PersonMapper;
import ru.ssv.delphin.mapper.TaskMapper;
import ru.ssv.delphin.service.PersonService;
import ru.ssv.delphin.service.TaskService;
import ru.ssv.delphin.service.impl.PersonServiceImpl;
import ru.ssv.delphin.service.impl.TaskServiceImpl;

@Configuration
public class AppConfiguration {

    @Bean
    PersonService personService(PersonRepository personRepository, PersonMapper personMapper) {
        return new PersonServiceImpl(personRepository, personMapper);
    }

    @Bean
    TaskService taskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        return new TaskServiceImpl(taskRepository, taskMapper);
    }
}
