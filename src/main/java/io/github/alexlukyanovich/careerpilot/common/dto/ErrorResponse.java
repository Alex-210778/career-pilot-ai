package io.github.alexlukyanovich.careerpilot.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Error response")
public record ErrorResponse(
        @Schema(description = "Error timestamp", example = "2026-07-01T12:00:00Z")
        Instant timestamp,

        @Schema(description = "HTTP status code", example = "404")
        int status,

        @Schema(description = "Error code", example = "VACANCY_NOT_FOUND")
        String error,

        @Schema(description = "Human-readable error message", example = "Vacancy not found with id: 550e8400-e29b-41d4-a716-446655440000")
        String message,

        @Schema(description = "Request path", example = "/api/v1/vacancies/550e8400-e29b-41d4-a716-446655440000")
        String path
) {
}
