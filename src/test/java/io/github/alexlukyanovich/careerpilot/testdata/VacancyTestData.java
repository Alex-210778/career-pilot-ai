package io.github.alexlukyanovich.careerpilot.testdata;

import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.UpdateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.github.alexlukyanovich.careerpilot.vacancy.entity.Vacancy;
import io.github.alexlukyanovich.careerpilot.vacancy.enums.VacancyStatus;

import java.time.Instant;
import java.util.UUID;

public final class VacancyTestData {

    private VacancyTestData() {
    }

    public static CreateVacancyRequest.CreateVacancyRequestBuilder createRequest() {
        return CreateVacancyRequest.builder()
                .title("Java Developer")
                .company("Amazon")
                .location("USA")
                .sourceUrl("https://amazon.com")
                .description("Looking for Backend Developer");
    }

    public static UpdateVacancyRequest.UpdateVacancyRequestBuilder updateRequest() {
        return UpdateVacancyRequest.builder()
                .title("Senior Java Developer")
                .company("Amazon")
                .location("USA")
                .sourceUrl("https://amazon.com")
                .description("Looking for a skilled backend engineer with over 6 years of experience.");
    }

    public static VacancyResponse.VacancyResponseBuilder response(UUID id) {
        return VacancyResponse.builder()
                .id(id)
                .title("Java Developer")
                .company("Amazon")
                .location("USA")
                .sourceUrl("https://amazon.com")
                .description("Looking for Backend Developer")
                .status(VacancyStatus.NEW)
                .createdAt(Instant.now());
    }

    public static Vacancy vacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Java Developer");
        vacancy.setCompany("Amazon");
        vacancy.setLocation("USA");
        vacancy.setSourceUrl("https://amazon.com");
        vacancy.setDescription("Looking for Backend Developer");
        vacancy.setStatus(VacancyStatus.NEW);
        return vacancy;
    }

    public static Vacancy savedVacancy(UUID id) {
        Vacancy vacancy = vacancy();
        vacancy.setId(id);
        vacancy.setCreatedAt(Instant.now());
        return vacancy;
    }

    public static Vacancy savedVacancy(UUID id,
                                       UpdateVacancyRequest request) {
        Vacancy vacancy = new Vacancy();

        vacancy.setId(id);
        vacancy.setTitle(request.getTitle());
        vacancy.setCompany(request.getCompany());
        vacancy.setLocation(request.getLocation());
        vacancy.setSourceUrl(request.getSourceUrl());
        vacancy.setDescription(request.getDescription());
        vacancy.setStatus(VacancyStatus.NEW);
        vacancy.setCreatedAt(Instant.now());

        return vacancy;
    }
}
