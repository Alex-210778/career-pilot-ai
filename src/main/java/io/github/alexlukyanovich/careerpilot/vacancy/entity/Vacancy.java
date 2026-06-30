package io.github.alexlukyanovich.careerpilot.vacancy.entity;

import io.github.alexlukyanovich.careerpilot.vacancy.enums.VacancyStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "title",
            nullable = false)
    private String title;

    @Column(name = "company",
            nullable = false)
    private String company;

    @Column(name = "location")
    private String location;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "TEXT")
    private String description;

    /**
     * Current vacancy processing status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private VacancyStatus status = VacancyStatus.NEW;

    @Column(name = "created_at",
            nullable = false,
            updatable = false)
    private Instant createdAt;

    @PrePersist
    private void prePersist() {
        createdAt = Instant.now();
    }
}
