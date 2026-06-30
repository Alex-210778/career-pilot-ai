package io.github.alexlukyanovich.careerpilot.vacancy.repository;

import io.github.alexlukyanovich.careerpilot.testdata.VacancyTestData;
import io.github.alexlukyanovich.careerpilot.vacancy.entity.Vacancy;
import io.github.alexlukyanovich.careerpilot.vacancy.enums.VacancyStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VacancyRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    void saveShouldPersistVacancy() {
        Vacancy vacancy = VacancyTestData.vacancy();

        Vacancy savedVacancy  = vacancyRepository.save(vacancy);

        assertNotNull(savedVacancy.getId());
        assertEquals("Java Developer", savedVacancy.getTitle());
        assertEquals("Amazon", savedVacancy.getCompany());
        assertEquals(VacancyStatus.NEW, savedVacancy.getStatus());
        assertNotNull(savedVacancy.getCreatedAt());
    }

    @Test
    void findByIdShouldReturnVacancyWhenVacancyExists() {
        Vacancy vacancy = VacancyTestData.vacancy();
        Vacancy savedVacancy  = vacancyRepository.save(vacancy);

        Optional<Vacancy> actualVacancy  = vacancyRepository.findById(savedVacancy.getId());

        assertTrue(actualVacancy.isPresent());
        assertEquals(savedVacancy.getId(), actualVacancy.get().getId());
        assertEquals(savedVacancy.getTitle(), actualVacancy.get().getTitle());
    }

    @Test
    void deleteShouldRemoveVacancy() {
        Vacancy vacancy = VacancyTestData.vacancy();
        Vacancy savedVacancy  = vacancyRepository.save(vacancy);

        vacancyRepository.delete(savedVacancy);

        Optional<Vacancy> actualVacancy  = vacancyRepository.findById(savedVacancy.getId());

        assertTrue(actualVacancy.isEmpty());
    }
}
