package io.github.alexlukyanovich.careerpilot.vacancy.controller;

import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.UpdateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.github.alexlukyanovich.careerpilot.vacancy.service.VacancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Vacancies", description = "Vacancy management API")
@RestController
@RequestMapping("/api/v1/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @Operation(
            summary = "Create vacancy",
            description = "Creates a new vacancy and returns the created entity."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vacancy created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyResponse create(@Valid @RequestBody CreateVacancyRequest request) {
        return vacancyService.create(request);
    }

    @Operation(
            summary = "Get all vacancies",
            description = "Returns all saved vacancies."
    )
    @ApiResponse(responseCode = "200", description = "Vacancies returned")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyResponse> findAll() {
        return vacancyService.findAll();
    }

    @Operation(
            summary = "Get vacancy by ID",
            description = "Returns vacancy by UUID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vacancy found"),
            @ApiResponse(responseCode = "404", description = "Vacancy not found")
    })
    @GetMapping("/{id}")
    public VacancyResponse findById(@PathVariable UUID id) {
        return vacancyService.findById(id);
    }

    @Operation(
            summary = "Update vacancy",
            description = "Updates existing vacancy data."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vacancy updated"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Vacancy not found")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyResponse update(@PathVariable UUID id,
                                  @Valid @RequestBody UpdateVacancyRequest request) {
        return vacancyService.update(id, request);
    }

    @Operation(
            summary = "Delete vacancy",
            description = "Deletes vacancy by UUID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vacancy deleted"),
            @ApiResponse(responseCode = "404", description = "Vacancy not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        vacancyService.deleteById(id);
    }
}
