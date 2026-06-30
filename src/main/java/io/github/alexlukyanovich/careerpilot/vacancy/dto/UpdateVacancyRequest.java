package io.github.alexlukyanovich.careerpilot.vacancy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Schema(description = "Request for updating vacancy data")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVacancyRequest {

    @Schema(description = "Vacancy title",
            example = "Java Developer")
    @NotBlank
    @Size(max = 255)
    private String title;

    @Schema(description = "Company name",
            example = "Amazon")
    @NotBlank
    @Size(max = 255)
    private String company;

    @Schema(description = "Vacancy location",
            example = "Remote")
    @Size(max = 255)
    private String location;

    @Schema(description = "Original vacancy URL",
            example = "https://example.com/jobs/java-developer")
    @Size(max = 1000)
    @Pattern(regexp = "^https?://.+",
            message = "Source URL must start with http:// or https://")
    private String sourceUrl;

    @Schema(description = "Full vacancy description",
            example = "We are looking for a Java Developer with Spring Boot experience.")
    @NotBlank
    private String description;
}
