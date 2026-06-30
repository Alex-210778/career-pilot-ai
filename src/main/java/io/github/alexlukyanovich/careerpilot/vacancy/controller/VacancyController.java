package io.github.alexlukyanovich.careerpilot.vacancy.controller;

import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.UpdateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.github.alexlukyanovich.careerpilot.vacancy.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyResponse create(@Valid @RequestBody CreateVacancyRequest request) {
        return vacancyService.create(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyResponse> findAll() {
        return vacancyService.findAll();
    }

    @GetMapping("/{id}")
    public VacancyResponse findById(@PathVariable UUID id) {
        return vacancyService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyResponse update(@PathVariable UUID id,
                                  @Valid @RequestBody UpdateVacancyRequest request) {
        return vacancyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        vacancyService.deleteById(id);
    }
}
