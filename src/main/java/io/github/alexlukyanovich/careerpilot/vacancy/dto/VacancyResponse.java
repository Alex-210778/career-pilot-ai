package io.github.alexlukyanovich.careerpilot.vacancy.dto;

import io.github.alexlukyanovich.careerpilot.vacancy.enums.VacancyStatus;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponse {
    private UUID id;
    private String title;
    private String company;
    private String location;
    private String sourceUrl;
    private String description;
    private VacancyStatus status;
    private Instant createdAt;
}
