package ru.ssv.delphin.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ssv.delphin.exception.EntityNotFoundedException;
import ru.ssv.delphin.exception.OperationNotSupportedException;

import javax.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OperationNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleOperationNotSupportedException(OperationNotSupportedException ex,
                                                                          HttpServletRequest httpRequest) {
        return buildErrorResponseEntity(NOT_IMPLEMENTED, ex, httpRequest);
    }

    @ExceptionHandler(EntityNotFoundedException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundedException(OperationNotSupportedException ex,
                                                                                 HttpServletRequest httpRequest) {
        return buildErrorResponseEntity(NOT_FOUND, ex, httpRequest);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponseEntity(HttpStatus status,
                                                                   Exception ex,
                                                                   HttpServletRequest httpRequest) {
        var errorResponse = ErrorResponse.builder()
                .withUrl(httpRequest.getRequestURI())
                .withCode(status.value())
                .withMessage(ex.getMessage())
                .withTrace(mapStackTraceToString(ex))
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
