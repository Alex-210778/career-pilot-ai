package io.github.alexlukyanovich.careerpilot.vacancy.repository;

import io.github.alexlukyanovich.careerpilot.vacancy.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

}
