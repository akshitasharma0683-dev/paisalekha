# PaisaLekha 💸

A secure personal finance management backend application built using Java, Spring Boot, Spring Security, JWT Authentication, Hibernate, and PostgreSQL.

PaisaLekha helps users manage expenses, income, and categories while ensuring complete data isolation through JWT-based authentication and authorization.

---

## Project Overview

Managing personal finances requires tracking income, expenses, and spending patterns efficiently. PaisaLekha provides a secure backend system that enables users to:

* Manage personal expenses
* Track income sources
* Organize spending using categories
* Analyze financial activity
* Access only their own financial records

The application follows modern backend development practices using layered architecture, RESTful APIs, and secure authentication mechanisms.

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate ORM
* Maven

### Database

* PostgreSQL

### Security

* JWT Authentication
* BCrypt Password Encryption

### Tools

* Git
* GitHub
* Postman

---

## Features

### Authentication & Security

* User Registration
* User Login
* JWT Token Generation
* JWT-Based Authentication
* BCrypt Password Hashing
* Stateless Session Management
* Protected API Endpoints
* User Ownership Validation

### Category Management

* Create Categories
* Update Categories
* Delete Categories
* User-Specific Categories
* Default Categories
* Category Ownership Validation

### Expense Management

* Create Expenses
* Update Expenses
* Delete Expenses
* Category-Based Expense Tracking
* Weekly Expense Analytics
* Monthly Expense Analytics
* Payment Method Tracking

### Income Management

* Create Income Records
* Update Income Records
* Delete Income Records
* Income Source Tracking

### Dashboard Analytics

* Total Income
* Total Expenses
* Current Balance
* Recent Transactions
* Expense Statistics

---

## System Architecture

```text
Client
   ↓
Spring Security Filter Chain
   ↓
JWT Authentication Filter
   ↓
Controller Layer
   ↓
Service Layer
   ↓
Repository Layer
   ↓
PostgreSQL Database
```

The application follows a layered architecture that separates business logic, security, API handling, and persistence concerns.

---

## Entity Relationship Diagram

![ER Diagram](paisalekha/docs/er-diagram.png)

### Relationships

* One User → Many Categories
* One User → Many Expenses
* One User → Many Income Records
* One Category → Many Expenses

---

## Authentication Flow

```text
User Login
    ↓
Credentials Validated
    ↓
JWT Token Generated
    ↓
Client Stores Token
    ↓
Token Sent In Authorization Header
    ↓
JWT Filter Validates Token
    ↓
User Authenticated
    ↓
Protected Resource Access Granted
```

---

## Security Implementation

### Password Encryption

Passwords are encrypted before persistence using BCrypt.

```java
passwordEncoder.encode(password);
```

### JWT Authentication

JWT tokens are generated after successful login and validated on every protected request.

### Ownership Validation

Users can only access:

* Their own expenses
* Their own income records
* Their own categories

This prevents unauthorized access to another user's financial data.

---

## Key Engineering Decisions

### Layered Architecture

Controllers depend on service abstractions instead of implementation classes.

Benefits:

* Better Maintainability
* Easier Testing
* Cleaner Code Organization

---

### Database-Level Aggregation

Financial calculations are executed directly at the database level using custom JPA queries.

Example:

```java
@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user = :user")
```

Benefits:

* Reduced Memory Usage
* Faster Aggregation
* Improved Performance

---

### Automatic Entity Initialization

JPA lifecycle hooks are used to automatically initialize:

* Timestamps
* Default Roles
* Entity Metadata

---

## Challenges & Solutions

### Challenge 1: Securing APIs with JWT

**Problem**

After implementing JWT authentication, protected endpoints were returning authorization errors.

**Solution**

Implemented a custom JWT filter and populated Spring Security's SecurityContextHolder after successful token validation.

---

### Challenge 2: Preventing Unauthorized Data Access

**Problem**

Users should never access categories, expenses, or income records belonging to another user.

**Solution**

Implemented ownership validation using the authenticated user extracted from JWT tokens before performing business operations.

---

### Challenge 3: Secure Password Storage

**Problem**

Storing passwords directly in the database is insecure.

**Solution**

Integrated BCrypt password hashing before persistence and BCrypt matching during login.

---

## REST API Overview

### User APIs

```http
POST /user/register
POST /user/login
DELETE /user/{userId}
```

### Category APIs

```http
POST /categories
GET /categories
PUT /categories/{id}
DELETE /categories/{id}
```

### Expense APIs

```http
POST /expenses
GET /expenses
PUT /expenses/{id}
DELETE /expenses/{id}
GET /expenses/weekly
GET /expenses/monthly
```

### Income APIs

```http
POST /income
GET /income
PUT /income/{id}
DELETE /income/{id}
```

### Dashboard APIs

```http
GET /dashboard
```

---

## Future Enhancements

* DTO-Based Architecture
* Request Validation
* Global Exception Handling
* Spring AI Financial Insights
* Docker Deployment
* CI/CD Pipeline
* Budget Planning
* Email Notifications
* React Frontend Integration

---

## Local Setup

### Clone Repository

```bash
git clone <repository-url>
```

### Create Database

```sql
CREATE DATABASE expense_tracker;
```

### Configure Application Properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Author

Akshita Sharma

Java Backend Developer | Spring Boot Developer

Focused on building secure and scalable backend applications using Java, Spring Boot, REST APIs, PostgreSQL, and modern backend engineering practices.
