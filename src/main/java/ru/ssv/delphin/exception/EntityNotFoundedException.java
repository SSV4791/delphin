package ru.ssv.delphin.exception;

public class EntityNotFoundedException extends RuntimeException {
    public EntityNotFoundedException(String message) {
        super(message);
    }
}
