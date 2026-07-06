package io.github.alexlukyanovich.careerpilot.importer.provider;

import io.github.alexlukyanovich.careerpilot.importer.dto.ProviderVacancy;

import java.util.List;

/**
 * Defines a contract for importing vacancies from external job sources.
 * <p>
 * Implementations of this interface are responsible for retrieving vacancies
 * from a specific source (API, website, RSS feed, etc.) and converting them
 * into a common {@link ProviderVacancy} format.
 */
public interface VacancyImporter {

    /**
     * Imports vacancies from an external source.
     *
     * @return a list of imported vacancies in a common format;
     *         never {@code null}
     */
    List<ProviderVacancy> importVacancies();
}
