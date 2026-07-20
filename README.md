# Store

A RESTful e-commerce backend built with Spring Boot. It exposes APIs for managing users, products, categories, and shopping carts, secured with JWT-based authentication.

## Features

- **User management** — register users, update profiles, change passwords, and list/delete users.
- **Product catalog** — CRUD for products, filtering by category, and sorting by `id`, `name`, or `price`.
- **Shopping cart** — create carts, add/update/remove items, and clear a cart.
- **Authentication** — stateless JWT login with a custom authentication filter and BCrypt password hashing.
- **Validation** — request-body validation, including a custom `@Lowercase` constraint.
- **API documentation** — interactive Swagger UI via springdoc-openapi.
- **Database migrations** — schema managed with Flyway.

## Tech Stack

- Java 26
- Spring Boot 4.0.6 (Web, Data JPA, Security, Validation, Thymeleaf)
- MySQL
- Flyway (migrations)
- MapStruct (DTO mapping)
- Lombok
- JJWT (JSON Web Tokens)
- springdoc-openapi (Swagger UI)
- Spotless with Google Java Format

## Prerequisites

- JDK 26
- MySQL running locally on `localhost:3306`
- Maven (or use the bundled `mvnw` / `mvnw.cmd` wrapper)

## Getting Started

### 1. Configure the database

The application connects to a MySQL database named `store` (created automatically if it doesn't exist). Default datasource settings are in `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/store?createDatabaseIfNotExist=true
    username: root
    password: 223344
```

Adjust the username/password to match your local MySQL setup.

### 2. Set the JWT secret

The JWT signing secret is read from the `JWT_SECRET` environment variable (via an optional `.env` file). Create a `.env` file in the project root:

```properties
JWT_SECRET=your-base64-encoded-secret-key
```

### 3. Run the application

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Flyway applies the migrations in `src/main/resources/db/migration` on startup. The app starts on `http://localhost:8080`.

## API Documentation

Once running, open the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## API Overview

### Auth (`/auth`)

| Method | Endpoint          | Description                          | Auth |
|--------|-------------------|--------------------------------------|------|
| POST   | `/auth/login`     | Authenticate and receive a JWT       | No   |
| POST   | `/auth/validate`  | Validate a bearer token              | Yes  |

### Users (`/users`)

| Method | Endpoint                      | Description             | Auth |
|--------|-------------------------------|-------------------------|------|
| GET    | `/users`                      | List users (`sortBy`)   | Yes  |
| GET    | `/users/{id}`                 | Get a user              | Yes  |
| POST   | `/users`                      | Register a new user     | No   |
| PUT    | `/users/{id}`                 | Update a user           | Yes  |
| DELETE | `/users/{id}`                 | Delete a user           | Yes  |
| POST   | `/users/{id}/change-password` | Change a user password  | Yes  |

### Products (`/products`)

| Method | Endpoint          | Description                              | Auth |
|--------|-------------------|------------------------------------------|------|
| GET    | `/products`       | List products (`categoryId`, `sortBy`)   | Yes  |
| GET    | `/products/{id}`  | Get a product                            | Yes  |
| POST   | `/products`       | Create a product                         | Yes  |
| PUT    | `/products/{id}`  | Update a product                         | Yes  |
| DELETE | `/products/{id}`  | Delete a product                         | Yes  |

### Carts (`/carts`)

| Method | Endpoint                            | Description               | Auth |
|--------|-------------------------------------|---------------------------|------|
| POST   | `/carts`                            | Create a cart             | No   |
| GET    | `/carts`                            | List all carts            | No   |
| GET    | `/carts/{cartId}`                   | Get a cart                | No   |
| POST   | `/carts/{cartId}/items`             | Add an item to a cart     | No   |
| PUT    | `/carts/{cartId}/items/{productId}` | Update item quantity      | No   |
| DELETE | `/carts/{cartId}/items/{productId}` | Remove an item            | No   |
| DELETE | `/carts/{cartId}/items`             | Clear the cart            | No   |

> Endpoints marked "Auth: Yes" require an `Authorization: Bearer <token>` header. All others are publicly accessible per the security configuration in `SecurityConfig`.

## Project Structure

```
src/main/java/com/example/store/
├── config/         # Security configuration
├── controllers/    # REST controllers and global exception handling
├── dtos/           # Request/response data transfer objects
├── entities/       # JPA entities
├── exceptions/     # Custom exceptions
├── filters/        # JWT authentication filter
├── mapper/         # MapStruct mappers
├── repositories/   # Spring Data JPA repositories
├── services/       # Business logic (cart, user, JWT)
└── validation/     # Custom validators
src/main/resources/
├── db/migration/   # Flyway SQL migrations
├── templates/      # Thymeleaf templates
└── application.yaml
```

## Building

```bash
# Run tests
./mvnw test

# Package a runnable JAR
./mvnw clean package
```

Code formatting is enforced by Spotless (Google Java Format). Run `./mvnw spotless:apply` to format before committing.
