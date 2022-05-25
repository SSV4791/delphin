package ru.ssv.delphin.validator;

import java.util.List;

public interface Validator<T> {
    boolean validate(T entity, List<String> errorMessages);
}
