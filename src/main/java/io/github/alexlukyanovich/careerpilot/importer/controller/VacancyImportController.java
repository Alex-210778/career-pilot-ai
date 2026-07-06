package io.github.alexlukyanovich.careerpilot.importer.controller;

import io.github.alexlukyanovich.careerpilot.importer.service.VacancyImportService;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Vacancy Import",
        description = "API for importing vacancies from external sources"
)
@RestController
@RequestMapping("/api/v1/import/vacancies")
@RequiredArgsConstructor
public class VacancyImportController {

    private final VacancyImportService vacancyImportService;

    @Operation(
            summary = "Import vacancy",
            description = "Imports vacancies from the configured external source and saves them in the application."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vacancies imported"),
            @ApiResponse(responseCode = "500", description = "Import failed")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<VacancyResponse> importVacancies() {
        return vacancyImportService.importVacancies();
    }

}
