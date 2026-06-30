package io.github.alexlukyanovich.careerpilot.vacancy.service;

import io.github.alexlukyanovich.careerpilot.testdata.VacancyTestData;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.CreateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.UpdateVacancyRequest;
import io.github.alexlukyanovich.careerpilot.vacancy.dto.VacancyResponse;
import io.github.alexlukyanovich.careerpilot.vacancy.entity.Vacancy;
import io.github.alexlukyanovich.careerpilot.vacancy.exception.VacancyNotFoundException;
import io.github.alexlukyanovich.careerpilot.vacancy.mapper.VacancyMapper;
import io.github.alexlukyanovich.careerpilot.vacancy.repository.VacancyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private VacancyMapper vacancyMapper;

    @InjectMocks
    private VacancyService vacancyService;

    private UUID vacancyId;
    private CreateVacancyRequest createRequest;
    private UpdateVacancyRequest updateRequest;
    private Vacancy vacancy;
    private Vacancy savedVacancy;
    private VacancyResponse vacancyResponse;

    @BeforeEach
    void setUp() {
        vacancyId = UUID.randomUUID();
        createRequest = VacancyTestData.createRequest().build();
        updateRequest = VacancyTestData.updateRequest().build();
        vacancy = VacancyTestData.vacancy();
        savedVacancy = VacancyTestData.savedVacancy(vacancyId);
        vacancyResponse = VacancyTestData.response(vacancyId).build();
    }

    @Test
    void createShouldSaveVacancyAndReturnResponse() {
        when(vacancyMapper.toEntity(createRequest)).thenReturn(vacancy);
        when(vacancyRepository.save(vacancy)).thenReturn(savedVacancy);
        when(vacancyMapper.toResponse(savedVacancy)).thenReturn(vacancyResponse);

        VacancyResponse actualResponse = vacancyService.create(createRequest);

        assertNotNull(actualResponse);
        assertEquals(vacancyResponse, actualResponse);

        ArgumentCaptor<Vacancy> vacancyCaptor = ArgumentCaptor.forClass(Vacancy.class);

        verify(vacancyMapper).toEntity(createRequest);
        verify(vacancyRepository).save(vacancyCaptor.capture());
        verify(vacancyMapper).toResponse(savedVacancy);

        Vacancy capturedVacancy = vacancyCaptor.getValue();

        assertAll(
                () -> assertEquals(createRequest.getTitle(), capturedVacancy.getTitle()),
                () -> assertEquals(createRequest.getCompany(), capturedVacancy.getCompany()),
                () -> assertEquals(createRequest.getLocation(), capturedVacancy.getLocation()),
                () -> assertEquals(createRequest.getSourceUrl(), capturedVacancy.getSourceUrl()),
                () -> assertEquals(createRequest.getDescription(), capturedVacancy.getDescription())
        );

        verifyNoMoreInteractions(vacancyRepository, vacancyMapper);
    }

    @Test
    void findByIdShouldReturnVacancyWhenVacancyExists() {
        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.of(savedVacancy));
        when(vacancyMapper.toResponse(savedVacancy)).thenReturn(vacancyResponse);

        VacancyResponse actualResponse = vacancyService.findById(vacancyId);

        assertNotNull(actualResponse);
        assertEquals(vacancyResponse, actualResponse);

        verify(vacancyRepository).findById(vacancyId);
        verify(vacancyMapper).toResponse(savedVacancy);

        verifyNoMoreInteractions(vacancyRepository, vacancyMapper);
    }

    @Test
    void findByIdShouldThrowVacancyNotFoundExceptionWhenVacancyDoesNotExist() {
        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.empty());

        assertThrows(VacancyNotFoundException.class,
                () -> vacancyService.findById(vacancyId));

        verify(vacancyRepository).findById(vacancyId);
        verifyNoInteractions(vacancyMapper);
        verifyNoMoreInteractions(vacancyRepository);
    }

    @Test
    void findAllShouldReturnAllVacancies() {
        UUID secondVacancyId = UUID.randomUUID();

        Vacancy secondVacancy = VacancyTestData.savedVacancy(secondVacancyId);
        secondVacancy.setTitle("Backend Developer");
        secondVacancy.setCompany("Netflix");
        secondVacancy.setLocation("Remote");
        secondVacancy.setSourceUrl("https://jobs.netflix.com/");
        secondVacancy.setDescription("Backend role");

        VacancyResponse secondResponse = VacancyTestData.response(secondVacancyId)
                .title(secondVacancy.getTitle())
                .company(secondVacancy.getCompany())
                .location(secondVacancy.getLocation())
                .sourceUrl(secondVacancy.getSourceUrl())
                .description(secondVacancy.getDescription())
                .status(secondVacancy.getStatus())
                .createdAt(secondVacancy.getCreatedAt())
                .build();

        when(vacancyRepository.findAll()).thenReturn(List.of(savedVacancy, secondVacancy));
        when(vacancyMapper.toResponse(savedVacancy)).thenReturn(vacancyResponse);
        when(vacancyMapper.toResponse(secondVacancy)).thenReturn(secondResponse);

        List<VacancyResponse> actualResponses = vacancyService.findAll();

        assertAll(
                () -> assertEquals(2, actualResponses.size()),
                () -> assertTrue(actualResponses.contains(vacancyResponse)),
                () -> assertTrue(actualResponses.contains(secondResponse))
        );

        verify(vacancyRepository).findAll();
        verify(vacancyMapper).toResponse(savedVacancy);
        verify(vacancyMapper).toResponse(secondVacancy);

        verifyNoMoreInteractions(vacancyRepository, vacancyMapper);
    }

    @Test
    void updateShouldUpdateVacancyAndReturnResponseWhenVacancyExists() {
        Vacancy updatedVacancy = VacancyTestData.savedVacancy(vacancyId, updateRequest);

        VacancyResponse updatedResponse = VacancyTestData.response(updatedVacancy.getId())
                .title(updatedVacancy.getTitle())
                .company(updatedVacancy.getCompany())
                .location(updatedVacancy.getLocation())
                .sourceUrl(updatedVacancy.getSourceUrl())
                .description(updatedVacancy.getDescription())
                .createdAt(updatedVacancy.getCreatedAt())
                .build();

        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.of(savedVacancy));
        doNothing().when(vacancyMapper).updateEntityFromRequest(updateRequest, savedVacancy);
        when(vacancyMapper.toResponse(savedVacancy)).thenReturn(updatedResponse);

        VacancyResponse actualResponse = vacancyService.update(vacancyId, updateRequest);

        assertNotNull(actualResponse);
        assertEquals(updatedResponse, actualResponse);

        verify(vacancyRepository).findById(vacancyId);
        verify(vacancyMapper).updateEntityFromRequest(updateRequest, savedVacancy);
        verify(vacancyMapper).toResponse(savedVacancy);

        verifyNoMoreInteractions(vacancyRepository, vacancyMapper);
    }

    @Test
    void updateShouldThrowVacancyNotFoundExceptionWhenVacancyDoesNotExist() {
        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.empty());

        assertThrows(VacancyNotFoundException.class,
                () -> vacancyService.update(vacancyId, updateRequest));

        verify(vacancyRepository).findById(vacancyId);
        verifyNoInteractions(vacancyMapper);
        verifyNoMoreInteractions(vacancyRepository);
    }

    @Test
    void deleteByIdShouldDeleteVacancyWhenVacancyExists() {
        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.of(savedVacancy));

        vacancyService.deleteById(vacancyId);

        verify(vacancyRepository).findById(vacancyId);
        verify(vacancyRepository).delete(savedVacancy);
        verifyNoInteractions(vacancyMapper);
        verifyNoMoreInteractions(vacancyRepository);
    }

    @Test
    void deleteByIdShouldThrowVacancyNotFoundExceptionWhenVacancyDoesNotExist() {
        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.empty());

        assertThrows(VacancyNotFoundException.class,
                () -> vacancyService.deleteById(vacancyId));

        verify(vacancyRepository).findById(vacancyId);
        verifyNoInteractions(vacancyMapper);
        verifyNoMoreInteractions(vacancyRepository);
    }
}
