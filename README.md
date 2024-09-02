# Spring Boot Backend with PostgreSQL Using Docker

This guide provides steps to configure a Spring Boot backend application to use a PostgreSQL database running in a Docker container instead of the default H2 database.

## Prerequisites

- Docker installed on your machine
- Java and Maven installed
- Basic knowledge of Spring Boot

## Step 1: Set Up PostgreSQL Using Docker

Run the following command to start a PostgreSQL container:

```bash
docker run --name postgres-container -e POSTGRES_DB=mydb -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -d postgres
```

POSTGRES_DB: Name of the database (replace mydb with your preferred database name).
POSTGRES_USER: Username for accessing the database (replace myuser with your preferred username).
POSTGRES_PASSWORD: Password for accessing the database (replace mypassword with your preferred password).
-p 5432:5432: Maps port 5432 on your local machine to port 5432 on the container.
Step 2: Update Application Properties
Edit your src/main/resources/application.properties file (or use application.yml) to configure the PostgreSQL connection.

application.properties
```bash
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mydb
    username: myuser
    password: mypassword
    driver-class-name: org.postgresql.Driver
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
```

Step 3: Add PostgreSQL Dependency
Add the PostgreSQL JDBC driver dependency to your pom.xml file.

Maven (pom.xml)
```bash
<dependencies>
    <!-- Other dependencies -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.18</version>
    </dependency>
</dependencies>
```
Step 4: Ensure Docker Container is Running
Make sure the PostgreSQL Docker container is running by starting it with:

```bash

docker start postgres-container
```
You can verify that the container is running by using:

```bash

docker ps
```
Step 5: Test Your Application
Run your Spring Boot application. It should now connect to the PostgreSQL database running in the Docker container using the configuration specified in application.properties or application.yml.

You can run the Spring Boot application using:

```bash

mvn spring-boot:run
```
Step 6: Handling Database Initialization
To initialize your database schema and insert initial data, you can use schema.sql and data.sql files.

schema.sql: This file should contain SQL statements to create your database schema (e.g., create tables).
data.sql: This file should contain SQL statements to insert initial data into your database.
Place these files in the src/main/resources directory. Spring Boot will automatically execute them on startup.

Step 7: Docker Compose (Optional)
For a more integrated setup, consider using Docker Compose to manage both the Spring Boot application and the PostgreSQL service together.

Create a docker-compose.yml file in your project root:
```bash
version: '3.8'

services:
  postgres:
    image: postgres:latest
    environment:
      POSTGRES_DB: mydb
      POSTGRES_USER: myuser
      POSTGRES_PASSWORD: mypassword
    ports:
      - "5432:5432"

  app:
    image: your-spring-boot-app-image
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/mydb
      SPRING_DATASOURCE_USERNAME: myuser
      SPRING_DATASOURCE_PASSWORD: mypassword
    depends_on:
      - postgres
    ports:
      - "8080:8080"
```
Build and run the containers:
```bash
docker-compose up --build
```
This will set up both your Spring Boot application and PostgreSQL container together.


```
# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/mainbackend
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate Dialect
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
