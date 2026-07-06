package io.github.alexlukyanovich.careerpilot.importer.dto;

public record ProviderVacancy(
        String title,
        String company,
        String location,
        String sourceUrl,
        String description
) {

}
