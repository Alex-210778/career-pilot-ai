package io.github.alexlukyanovich.careerpilot.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVacancyRequest {
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String company;

    @Size(max = 255)
    private String location;

    @Size(max = 1000)
    @Pattern(regexp = "^https?://.+",
            message = "Source URL must start with http:// or https://")
    private String sourceUrl;

    @NotBlank
    private String description;
}
