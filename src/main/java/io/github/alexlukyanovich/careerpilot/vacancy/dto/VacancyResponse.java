package io.github.alexlukyanovich.careerpilot.vacancy.dto;

import io.github.alexlukyanovich.careerpilot.vacancy.enums.VacancyStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Vacancy response")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponse {

    @Schema(description = "Vacancy ID",
            example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Vacancy title",
            example = "Senior Java Developer")
    private String title;

    @Schema(description = "Company name",
            example = "Amazon")
    private String company;

    @Schema(description = "Vacancy location",
            example = "Remote")
    private String location;

    @Schema(description = "Original vacancy URL",
            example = "https://example.com/jobs/java-developer")
    private String sourceUrl;

    @Schema(description = "Full vacancy description")
    private String description;

    @Schema(description = "Vacancy status",
            example = "NEW")
    private VacancyStatus status;

    @Schema(description = "Vacancy creation time",
            example = "2026-07-01T12:00:00Z")
    private Instant createdAt;
}
