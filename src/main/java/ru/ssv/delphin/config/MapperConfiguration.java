package ru.ssv.delphin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ssv.delphin.mapper.PersonMapper;
import ru.ssv.delphin.mapper.PersonMapperImpl;
import ru.ssv.delphin.mapper.TaskMapper;
import ru.ssv.delphin.mapper.TaskMapperImpl;

@Configuration
public class MapperConfiguration {
    @Bean
    PersonMapper personMapper(TaskMapper taskMapper) {
        return new PersonMapperImpl(taskMapper);
    }

    @Bean
    TaskMapper taskMapper() {
        return new TaskMapperImpl();
    }
}
