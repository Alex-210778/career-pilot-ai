package io.github.alexlukyanovich.careerpilot.importer.mock;

import io.github.alexlukyanovich.careerpilot.importer.dto.ProviderVacancy;
import io.github.alexlukyanovich.careerpilot.importer.provider.VacancyImporter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockVacancyImporter implements VacancyImporter {

    @Override
    public List<ProviderVacancy> importVacancies() {
        return List.of(
                new ProviderVacancy(
                        "Java Backend Developer",
                        "JetBrains",
                        "Remote",
                        "https://example.com/jobs/java-backend-developer",
                        "We are looking for a Java Backend Developer with Spring Boot experience."
                ),
                new ProviderVacancy(
                        "Middle Java Developer",
                        "EPAM",
                        "Poland",
                        "https://example.com/jobs/middle-java-developer",
                        "Java, Spring Boot, PostgreSQL, Docker, microservices."
                )
        );
    }
}
