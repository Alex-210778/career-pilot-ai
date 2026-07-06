package io.github.alexlukyanovich.careerpilot.importer.service;

import io.github.alexlukyanovich.careerpilot.importer.dto.ProviderVacancy;
import io.github.alexlukyanovich.careerpilot.importer.provider.VacancyImporter;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.github.alexlukyanovich.careerpilot.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Imports vacancies from external sources and stores them using the vacancy service.
 */
@Service
@RequiredArgsConstructor
public class VacancyImportService {

    private final VacancyImporter vacancyImporter;
    private final VacancyService vacancyService;

    /**
     * Imports vacancies from the configured importer and stores them in the application.
     *
     * @return list of saved vacancies
     */
    public List<VacancyResponse> importVacancies() {
        return vacancyImporter.importVacancies().stream()
                .map(this::toCreateVacancyRequest)
                .map(vacancyService::create)
                .toList();
    }

    private CreateVacancyRequest toCreateVacancyRequest(ProviderVacancy providerVacancy) {
        return CreateVacancyRequest.builder()
                .title(providerVacancy.title())
                .company(providerVacancy.company())
                .location(providerVacancy.location())
                .sourceUrl(providerVacancy.sourceUrl())
                .description(providerVacancy.description())
                .build();
    }
}
