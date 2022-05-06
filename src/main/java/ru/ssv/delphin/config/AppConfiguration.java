package ru.ssv.delphin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ssv.delphin.service.impl.InMemoryBaseServiceImpl;
import ru.ssv.delphin.service.impl.InMemoryPersonServiceImpl;
import ru.ssv.delphin.service.impl.InMemoryTaskServiceImpl;

@Configuration
public class AppConfiguration {
    @Bean
    public InMemoryBaseServiceImpl inMemoryBaseService() {
        return new InMemoryBaseServiceImpl();
    }

    @Bean
    public InMemoryPersonServiceImpl inMemoryPersonService(InMemoryBaseServiceImpl inMemoryBaseService) {
        return new InMemoryPersonServiceImpl(inMemoryBaseService);
    }

    @Bean
    public InMemoryTaskServiceImpl inMemoryTaskService(InMemoryBaseServiceImpl inMemoryBaseService) {
        return new InMemoryTaskServiceImpl(inMemoryBaseService);
    }
}
