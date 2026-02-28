# Hotel Reservation System

REST API is a Java + Spring Boot application for managing the hotel booking system.

[SonarCloud](https://sonarcloud.io/summary/overall?id=SosiskaKiller812_hotel-reservation&branch=main)

## About the project

The project implements the basic architecture of a server application using:

- Spring Boot
- Spring Web
- Spring Data JPA
- Relational database(postgres)
- DTOs and mappers
- Layered architecture (Controller → Service → Repository)

## Implemented

- CRUD operations
- 5 entities:
- Hotel
  - Room
  - Booking
  - Address
  - Convenience
- Connections:
- OneToMany
- ManyToMany
- Working with CascadeType and FetchType
- Demonstration and solution of the N+1 problem
- Using `@Transactional`
- DTO layer and mapping Entity ↔ DTO
- Checkstyle is configured

The project is developing in stages and will be further expanded with additional functionality.
