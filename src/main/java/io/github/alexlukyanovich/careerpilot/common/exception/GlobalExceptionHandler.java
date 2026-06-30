package io.github.alexlukyanovich.careerpilot.common.exception;

import io.github.alexlukyanovich.careerpilot.common.dto.ErrorResponse;
import io.github.alexlukyanovich.careerpilot.common.enums.ErrorCode;
import io.github.alexlukyanovich.careerpilot.vacancy.exception.VacancyNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VacancyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleVacancyNotFound(
            VacancyNotFoundException exception,
            HttpServletRequest request) {
        return new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ErrorCode.VACANCY_NOT_FOUND.name(),
                exception.getMessage(),
                request.getRequestURI()
        );
    }
}
