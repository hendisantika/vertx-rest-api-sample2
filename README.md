# Vert.x REST API Sample 2

[![Java CI with Maven](https://github.com/hendisantika/vertx-rest-api-sample2/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/vertx-rest-api-sample2/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
[![Vert.x](https://img.shields.io/badge/vert.x-5.2.2-purple.svg)](https://vertx.io)
![Hibernate](https://img.shields.io/badge/Hibernate-5.6-59666C?logo=hibernate)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue?logo=postgresql&logoColor=white)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

Sample CRUD REST API built with **Eclipse Vert.x 5** (Vert.x Web), **JPA/Hibernate** and **PostgreSQL**, running on **JDK 25**.

## Tech Stack

| Component        | Version              |
|------------------|----------------------|
| Java (JDK)       | 25 (Temurin in CI)   |
| Eclipse Vert.x   | 5.2.2 (core, web, launcher-application) |
| Hibernate ORM    | 5.6.15.Final (`javax.persistence`) |
| PostgreSQL JDBC  | 42.7.x               |
| JUnit Jupiter    | 6.x + `vertx-junit5` |
| Build            | Maven Wrapper (`./mvnw`) |

## Prerequisites

- JDK **25**
- PostgreSQL running on `localhost:5432` with a database named `position`

Database connection is configured in [`src/main/resources/META-INF/persistence.xml`](src/main/resources/META-INF/persistence.xml):

```xml
<property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/position"/>
<property name="hibernate.connection.username" value="yu71"/>
<property name="hibernate.connection.password" value="53cret"/>
<property name="hibernate.hbm2ddl.auto" value="update"/>
```

Quick start with Docker:

```shell
docker run -d --name vertx-pg -p 5432:5432 \
  -e POSTGRES_DB=position -e POSTGRES_USER=yu71 -e POSTGRES_PASSWORD=53cret \
  postgres:latest
```

> The database is only touched on the first request, so the verticle (and the tests) start without PostgreSQL.

## Building

Run the tests:

```shell
./mvnw clean test
```

Package the application (produces `target/vertx-rest-api-sample2-1.0.0-SNAPSHOT-fat.jar`):

```shell
./mvnw clean package
```

## Running

From Maven:

```shell
./mvnw clean compile exec:java
```

Or from the fat jar:

```shell
java -jar target/vertx-rest-api-sample2-1.0.0-SNAPSHOT-fat.jar
```

The HTTP server listens on **http://localhost:8080**.

## API Endpoints

| Method   | Path           | Description                                  |
|----------|----------------|----------------------------------------------|
| `GET`    | `/position`    | List all positions                           |
| `GET`    | `/user`        | List all users                               |
| `GET`    | `/user/:id`    | Get a user by CPF                            |
| `POST`   | `/user`        | Create a user                                |
| `PUT`    | `/user`        | Update a user                                |
| `DELETE` | `/user/:id`    | Delete a user by CPF                         |
| `POST`   | `/user/filter` | Filter users by `name`, `status`, `profile`  |

Example:

```shell
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"cpf":"12345678900","name":"Hendi","email":"hendi@example.com","profile":1,"position":{"id":1},"phone":"0812"}'

curl -X POST http://localhost:8080/user/filter \
  -H "Content-Type: application/json" \
  -d '{"name":"hen","status":"AI","profile":99}'
```

## CI/CD & Dependency Management

- **GitHub Actions** ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) builds and tests the project on JDK 25 for every push and pull request to `main`, then submits the Maven dependency graph on `main`.
- **Dependabot** ([`.github/dependabot.yml`](.github/dependabot.yml)) checks Maven dependencies and GitHub Actions daily (05:00 Asia/Jakarta), groups related updates (Vert.x, JUnit, Maven plugins, persistence, Jackson, Actions) and uses conventional commit prefixes.

## Help

- [Vert.x Documentation](https://vertx.io/docs/)
- [Vert.x 5 Migration Guide](https://vertx.io/docs/guides/vertx-5-migration-guide/)
- [Vert.x Stack Overflow](https://stackoverflow.com/questions/tagged/vert.x?sort=newest&pageSize=15)
- [Vert.x Discord](https://discord.gg/6ry7aqPWXy)

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
