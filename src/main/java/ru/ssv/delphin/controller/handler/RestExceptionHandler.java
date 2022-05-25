package ru.ssv.delphin.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ssv.delphin.exception.EntityNotFoundedException;
import ru.ssv.delphin.exception.ModelValidationException;
import ru.ssv.delphin.exception.OperationNotSupportedException;

import javax.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OperationNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleOperationNotSupportedException(OperationNotSupportedException ex,
                                                                          HttpServletRequest httpRequest) {
        return buildErrorResponseEntity(NOT_IMPLEMENTED, ex, List.of(ex.getMessage()), httpRequest);
    }

    @ExceptionHandler(EntityNotFoundedException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundedException(EntityNotFoundedException ex,
                                                                                 HttpServletRequest httpRequest) {
        return buildErrorResponseEntity(NOT_FOUND, ex, List.of(ex.getMessage()), httpRequest);
    }

    @ExceptionHandler(ModelValidationException.class)
    protected ResponseEntity<ErrorResponse> handleModelValidationException(ModelValidationException ex,
                                                                            HttpServletRequest httpRequest) {
        return buildErrorResponseEntity(BAD_REQUEST, ex, ex.getText(), httpRequest);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponseEntity(HttpStatus status,
                                                                   Exception ex,
                                                                   List<String> text,
                                                                   HttpServletRequest httpRequest) {
        var errorResponse = ErrorResponse.builder()
                .withUrl(httpRequest.getRequestURI())
                .withCode(status.value())
                .withMessage(ex.getMessage())
                .withTrace(mapStackTraceToString(ex))
                .withText(text)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    private String mapStackTraceToString(Exception ex) {
        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
