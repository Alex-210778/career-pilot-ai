package io.github.alexlukyanovich.careerpilot.vacancy.dto;

import io.github.alexlukyanovich.careerpilot.vacancy.enums.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
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
