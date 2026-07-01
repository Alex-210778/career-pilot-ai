# CareerPilot AI

An AI-powered assistant that automates the job search process.

CareerPilot AI aggregates vacancies from multiple job platforms, analyzes how well they match a candidate's resume, generates personalized cover letters using Large Language Models (LLMs), and helps manage the entire application workflow.

> **Project Status:** 🚧 In Active Development

---

# Features

### Implemented

- ✅ Vacancy CRUD API
- ✅ PostgreSQL integration
- ✅ Database versioning with Liquibase
- ✅ REST API documentation with OpenAPI (Swagger)
- ✅ DTO mapping with MapStruct
- ✅ Unit tests with Mockito
- ✅ Integration tests with Testcontainers

### Planned

- ⏳ Resume management
- ⏳ AI-powered vacancy analysis
- ⏳ Resume improvement recommendations
- ⏳ AI-generated cover letters
- ⏳ Multi-provider LLM support
- ⏳ Vacancy import from multiple job platforms
- ⏳ Job application tracking
- ⏳ Telegram notifications

---

# Technology Stack

## Backend

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Hibernate

## Database

- PostgreSQL
- Liquibase

## Infrastructure

- Docker
- Docker Compose
- Testcontainers

## Documentation

- OpenAPI 3
- Swagger UI

## Mapping

- MapStruct

## Build Tool

- Gradle

## Testing

- JUnit 5
- Mockito
- Testcontainers

---

# Architecture

The project follows a layered architecture.

```
Controller
        │
        ▼
Service
        │
        ▼
Repository
        │
        ▼
PostgreSQL
```

Current architecture principles:

- Layered Architecture
- DTO Pattern
- Repository Pattern
- Dependency Injection
- SOLID principles

The long-term goal is to evolve the project into a modular AI platform using Strategy and Provider patterns for multiple LLM integrations.

---

# Getting Started

## Clone the repository

```bash
git clone https://github.com/Alex-210778/career-pilot-ai.git

cd career-pilot-ai
```

---

## Start PostgreSQL

```bash
docker compose up -d
```

---

## Run the application

```bash
./gradlew bootRun
```

---

## Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

# REST API

## Vacancies

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/vacancies` | Create vacancy |
| GET | `/api/v1/vacancies` | Get all vacancies |
| GET | `/api/v1/vacancies/{id}` | Get vacancy by ID |
| PUT | `/api/v1/vacancies/{id}` | Update vacancy |
| DELETE | `/api/v1/vacancies/{id}` | Delete vacancy |

---

# Testing

Run all tests

```bash
./gradlew test
```

The project includes:

- Unit Tests
- Repository Integration Tests
- PostgreSQL Testcontainers

---

# Roadmap

## Phase 1

- [x] Vacancy management
- [x] Liquibase
- [x] Docker
- [x] OpenAPI
- [x] Unit Tests
- [x] Integration Tests

## Phase 2

- [ ] Resume module
- [ ] Resume CRUD
- [ ] Resume versioning

## Phase 3

- [ ] AI Vacancy Analysis
- [ ] Match Score
- [ ] Missing Skills Detection
- [ ] Resume Improvement Suggestions

## Phase 4

- [ ] AI Cover Letter Generator
- [ ] Personalized Cover Letters
- [ ] Prompt Templates

## Phase 5

- [ ] Vacancy Providers
- [ ] Habr Career
- [ ] RemoteOK
- [ ] Greenhouse
- [ ] Lever
- [ ] Manual Vacancy Import

## Phase 6

- [ ] Multi-LLM Support
- [ ] OpenAI
- [ ] Claude
- [ ] Gemini
- [ ] Ollama

## Phase 7

- [ ] Job Application Tracker
- [ ] Application History
- [ ] Interview Tracking
- [ ] Telegram Notifications

---

# Project Goals

CareerPilot AI is being developed as a real-world AI application rather than a demonstration project.

The primary goals are:

- automate the job search process;
- improve resume quality using AI;
- generate high-quality personalized cover letters;
- support multiple AI providers;
- provide a clean, extensible architecture suitable for long-term development.

---

# License

This project is licensed under the MIT License.