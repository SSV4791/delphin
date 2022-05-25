package ru.ssv.delphin.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ModelValidationException extends RuntimeException{
    private static final String ERROR_MESSAGE = "Ошибка валидации DTO";
    private List<String> text;

    public ModelValidationException(List<String> text) {
        super(ERROR_MESSAGE);
        this.text = text;
    }
}
