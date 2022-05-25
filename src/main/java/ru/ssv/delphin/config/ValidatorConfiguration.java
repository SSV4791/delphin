package ru.ssv.delphin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ssv.delphin.api.model.Person;
import ru.ssv.delphin.validator.Validator;
import ru.ssv.delphin.validator.impl.CreatedPersonValidatorImpl;

@Configuration
public class ValidatorConfiguration {
    @Bean
    Validator<Person> createdPersonValidator() {
        return new CreatedPersonValidatorImpl();
    }
}
