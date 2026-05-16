# PaisaLekha 💸

PaisaLekha is a backend system for personal finance management built using Spring Boot, PostgreSQL, Spring Data JPA, and Spring Security.

The project supports:

* User authentication
* Expense & income tracking
* Category management
* Dashboard analytics
* User-specific financial records

---

# Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Security
* Hibernate ORM
* PostgreSQL
* Maven
* Git & GitHub

---

# Core Features

## User Module

* User registration & login
* BCrypt password encryption
* Duplicate username/email validation
* User deletion

## Category Module

* User-specific categories
* Unique category constraint per user

## Expense Module

* Expense tracking
* Category-based expenses
* Payment method & date tracking

## Income Module

* Income tracking
* Income source management

## Dashboard Module

* Total income & expense aggregation
* Balance calculation
* Expense statistics
* Recent transaction tracking

---

# Architecture

```text
Controller Layer
    ↓
Service Layer
    ↓
Repository Layer
    ↓
PostgreSQL Database
```

The project follows layered backend architecture with clear separation of responsibilities between API handling, business logic, and persistence.

---

# Entity Relationship Diagram

![ER Diagram](docs/er-diagram.png)

## Relationships

* One User → Many Categories
* One User → Many Expenses
* One User → Many Income Records
* One Category → Many Expenses

---

# Key Engineering Decisions

## Secure Password Handling

Passwords are encrypted using BCrypt before persistence.

Sensitive fields are protected using:

```java
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
```

This allows password input during authentication while preventing password exposure in API responses.

---

## Entity Lifecycle Management

JPA lifecycle hooks were implemented using:

```java
@PrePersist
```

This ensures reliable initialization of:

* Default user roles
* Entity timestamps

---

## Layered Service Abstraction

Controllers depend on service interfaces instead of implementation classes.

This improves:

* Maintainability
* Scalability
* Testability

---

## Dashboard Optimization

Dashboard aggregation was optimized using custom JPA queries instead of in-memory calculations.

Example:

```java
@Query("SELECT SUM(...)")
```

This reduced unnecessary entity loading and improved efficiency.

---

# REST APIs

## User APIs

```http
POST /user/register
POST /user/login
DELETE /user/{userId}
```

## Category APIs

```http
POST /api/categories
GET /api/categories
```

## Expense APIs

```http
POST /api/expenses
GET /api/expenses
```

## Income APIs

```http
POST /api/income
GET /api/income
```

## Dashboard APIs

```http
GET /api/dashboard
```

---

# Future Improvements

* JWT Authentication
* Role-based Authorization
* DTO-based response architecture
* Global exception handling
* Validation annotations
* Spring AI integration for financial insights
* Docker deployment
* CI/CD pipeline

---

# Setup

## Clone Repository

```bash
git clone <repository-url>
```

## Configure PostgreSQL

```sql
CREATE DATABASE expense_tracker;
```

## Configure Application Properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Run Project

```bash
mvn spring-boot:run
```

---

# Author

Akshita Sharma

Backend Developer | Java & Spring Boot Enthusiast
