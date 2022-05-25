package ru.ssv.delphin.validator.impl;

import org.springframework.util.CollectionUtils;
import ru.ssv.delphin.api.model.PersonCreate;
import ru.ssv.delphin.validator.Validator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class CreatedPersonValidatorImpl implements Validator<PersonCreate> {
    @Override
    public boolean validate(PersonCreate entity, List<String> errorMessages) {
        var localErrors = new ArrayList<String>();
        if (entity == null) {
            localErrors.add("Объект Person равен null");
        }
        if (isEmpty(entity.getName())) {
            localErrors.add(format("Атрибут name не должен быть пустым для объекта Person:%s", entity));
        }
        if (!CollectionUtils.isEmpty(localErrors)) {
            errorMessages.addAll(localErrors);
            return false;
        }
        return true;
    }
}
