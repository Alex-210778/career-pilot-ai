package io.github.alexlukyanovich.careerpilot.vacancy.service;

import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.UpdateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.github.alexlukyanovich.careerpilot.vacancy.entity.Vacancy;
import io.github.alexlukyanovich.careerpilot.vacancy.exception.VacancyNotFoundException;
import io.github.alexlukyanovich.careerpilot.vacancy.mapper.VacancyMapper;
import io.github.alexlukyanovich.careerpilot.vacancy.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Transactional
    public VacancyResponse create(CreateVacancyRequest request) {
        Vacancy vacancy = vacancyMapper.toEntity(request);

        return vacancyMapper.toResponse(vacancyRepository.save(vacancy));
    }

    @Transactional(readOnly = true)
    public VacancyResponse findById(UUID id) {
        Vacancy vacancyById = getVacancyById(id);

        return vacancyMapper.toResponse(vacancyById);
    }

    @Transactional(readOnly = true)
    public List<VacancyResponse> findAll() {
        List<Vacancy> vacancies = vacancyRepository.findAll();

        return vacancies.stream()
                .map(vacancyMapper::toResponse)
                .toList();
    }

    @Transactional
    public VacancyResponse update(UUID id, UpdateVacancyRequest request) {
        Vacancy vacancyById = getVacancyById(id);
        vacancyMapper.updateEntityFromRequest(request, vacancyById);

        return vacancyMapper.toResponse(vacancyById);
    }

    @Transactional
    public void deleteById(UUID id) {
        Vacancy vacancyById = getVacancyById(id);
        vacancyRepository.delete(vacancyById);
    }

    private Vacancy getVacancyById(UUID id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException(id));
    }
}
