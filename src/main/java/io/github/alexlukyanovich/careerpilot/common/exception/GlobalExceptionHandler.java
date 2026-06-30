package io.github.alexlukyanovich.careerpilot.common.exception;

import io.github.alexlukyanovich.careerpilot.vacancy.exception.VacancyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VacancyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleVacancyNotFound(VacancyNotFoundException exception) {
        return Map.of("message", exception.getMessage());
    }
}
