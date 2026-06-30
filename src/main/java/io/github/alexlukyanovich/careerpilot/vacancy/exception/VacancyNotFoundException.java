package io.github.alexlukyanovich.careerpilot.vacancy.exception;

import java.util.UUID;

public class VacancyNotFoundException extends RuntimeException {

    public VacancyNotFoundException(String message) {
        super(message);
    }

    public VacancyNotFoundException(UUID id) {
        super("Vacancy not found with id: " + id);
    }
}
