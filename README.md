# Product Price Service

This project is a **Spring Boot RESTful API service** created to solve a technical challenge. The service queries an in-memory **H2 database** to find the applicable price for a product based on a specific brand and application date, correctly handling price priority.

---

## Problem Description

The goal was to build a REST API service that could determine the correct price for a product from an e-commerce `PRICES` table.

The `PRICES` table stores different price tariffs for products belonging to a brand (e.g., ZARA) that are valid within a specific date range. A crucial business rule is the **PRIORITY** field: if two price tariffs overlap in their date range, the one with the **higher priority** (higher numeric value) must be applied.

### Data Model Example

| BRAND\_ID | START\_DATE | END\_DATE | PRICE\_LIST | PRODUCT\_ID | PRIORITY | PRICE | CURR |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1 | 35455 | 0 | 35.50 | EUR |
| 1 | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2 | 35455 | 1 | 25.45 | EUR |
| 1 | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3 | 35455 | 1 | 30.50 | EUR |
| 1 | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4 | 35455 | 1 | 38.95 | EUR |

---

## Solution Overview

### Technology Stack

* **Java 21**
* **Spring Boot 3** (including Spring Web, Spring Data JPA)
* **H2 Database:** In-memory database.
* **Maven:** Dependency management and build.
* **JUnit 5, Mockito & MockMvc:** For integration and unit testing.
* **Lombok:**

### Design

* **Controller (`PriceController`):** Exposes the REST API endpoint.
* **Service (`PriceService`):** Contains the business logic to find the correct price, including the priority disambiguation.
* **Repository (`PriceRepository`):** A Spring Data JPA repository to query the H2 database.
* **Entity (`Price`):** A JPA entity mapping to the `PRICES` table.
* **DTOs (`PriceQueryDTO`):** Data Transfer Objects are used for clean API request and response bodies.
* **Database Initialization:** The H2 database is automatically initialized on startup using `data.sql` and `schema.sql` located in `src/main/resources/`.

---

## 🏗️ Architecture: Hexagonal (Ports and Adapters)

The solution is designed following the **Hexagonal Architecture** (or **Ports and Adapters** pattern). This architectural style is chosen to ensure a strong separation between the **core business logic** and the external technical concerns, making the application highly maintainable, testable, and adaptable.

### Key Concepts

* **Inside the Hexagon (The Core):** This contains the **business logic** (`PriceService`) and the domain model (`Price` Entity). Its job is to manage the business rule: finding the applicable price based on priority.
* **Ports (Interfaces):** The core defines **Ports** (Java interfaces) that represent the boundaries.
    * **Driving Port (Primary):** Defines how external agents (like the API Controller) interact with the core. The `PriceService` interface is the driving port.
    * **Driven Port (Secondary):** Defines how the core needs to interact with external systems (like the database). The `PriceRepository` interface is the driven port.
* **Adapters (Implementations):** These are the technical components that plug into the Ports.
    * **Driving Adapter:** The **`PriceController`** maps the REST API endpoint (external technology) to the core's `PriceService` interface.
    * **Driven Adapter:** The **Spring Data JPA implementation** of the `PriceRepository` connects the core logic to the **H2 Database**.

This design ensures that the critical price prioritization logic is isolated and can be tested purely with **Unit Tests** and **Mocks** (using Mockito), independent of the underlying Spring configuration, HTTP details, or database technology.

---

## 🛠️ Detailed Explanation of the Technology Stack

The "Product Price Service" is implemented as a modern, efficient, and robust backend application leveraging the powerful **Java/Spring ecosystem**.

### Core Platform and Frameworks

| Technology | Role | Detailed Explanation |
| :--- | :--- | :--- |
| **Java 21** | **Programming Language (LTS)** | The foundational language of the project. Java 21 is a recent **Long-Term Support (LTS)** release, providing stability, performance enhancements, and modern features crucial for building reliable enterprise-grade services. |
| **Spring Boot 3** | **Application Framework** | The primary development framework. Spring Boot simplifies configuration through **auto-configuration**, manages dependency versions, and embeds an application server, drastically speeding up development and deployment. |
| **Spring Web** | **RESTful API Development** | Used specifically to build the RESTful services, handling the mapping of HTTP requests (e.g., the `GET` request to `/prices/find`) to controller methods and managing JSON serialization. |
| **Spring Data JPA** | **Data Persistence Abstraction** | Facilitates the implementation of the data access layer. It uses the Java Persistence API (JPA) standard to provide an **Object-Relational Mapping (ORM)** layer, allowing interaction with the database using Java entities. |

### Database and Data Management

| Technology | Role | Detailed Explanation |
| :--- | :--- | :--- |
| **H2 Database** | **Embedded/In-Memory Database** | A fast, lightweight database configured as **in-memory**. The data is stored in RAM during runtime, making it ideal for fast development, isolated testing, and proof-of-concept projects. |
| **Database Initialization** | **Schema and Data Loading** | The files `schema.sql` (defining the table structure) and `data.sql` (populating initial data) are automatically executed by Spring Boot on startup, ensuring a consistent and ready-to-use dataset for the service. |

### Testing and Development Tools

| Technology | Role | Detailed Explanation |
| :--- | :--- | :--- |
| **Maven** | **Build Automation** | Manages the entire project lifecycle, including automatic dependency downloading, code compilation, test execution, and packaging the final application into a deployable JAR file. |
| **JUnit 5** | **Testing Framework** | The standard for writing and running unit and integration tests in Java. |
| **Mockito** | **Mocking Framework** | Used to create **mock objects** for dependencies, allowing for the isolation of the business logic within the `PriceService` during **unit tests**, independent of the database. |
| **MockMvc** | **Controller Integration Testing** | A crucial Spring Test component that allows for an in-depth **integration test** of the API endpoints by simulating the full HTTP request and response cycle **without needing to start a real HTTP server**. This ensures the full flow, including the price prioritization logic, is validated efficiently. |
| **Lombok** | **Code Productivity** | Reduces Java verbosity by automatically generating common methods (getters, setters, constructors) at compile time via annotations, resulting in cleaner and more readable entity and DTO classes. |

---

## API Endpoint

The service provides one main endpoint to query the applicable price.

### Find Applicable Price

Returns the details of the price tariff to be applied based on the query parameters. The logic correctly identifies all matching time ranges and selects the one with the highest priority.

* **URL:** `/prices/find`
* **Method:** `GET`
* **Query Parameters:**
    * `applicationDate` (string): The date and time of application. (Format: `yyyy-MM-dd'T'HH:mm:ss`)
    * `productId` (long): The product identifier.
    * `brandId` (long): The brand identifier (e.g., 1 for ZARA).

* **Example Request:**
    ```bash
    curl -X GET "http://localhost:8080/prices/date/2020-06-15-20.00.00/productid/35455/brandid/1"
    ```

* **Success Response (200 OK):**
    ```json
    {
        "brandId":1,
        "productId":35455,
        "tariffId":4,
        "startDate":"2020-06-15T16:00:00",
        "endDate":"2020-12-31T23:59:59",
        "price":38.95
        }
    ```

* **Error Response (404 Not Found):**
  Returned if no price is found for the given criteria.
    ```json
    {
        "status":404,
        "message":"Not found price for product: 35455 and brand: 7 and date: 2020-06-15T20:00"
    }
    ```

---

## How to Run

### Prerequisites

* Java JDK 21 or later.
* Apache Maven 3.6 or later.

### Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/javierpart/product-price.git](https://github.com/javierpart/product-price.git)
    cd product-price
    ```

2.  **Build the project:**
    Use the Maven wrapper to build the project and package it.
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application:**
    You can run the application using the Spring Boot plugin:
    ```bash
    ./mvnw spring-boot:run
    ```
    Alternatively, you can run the packaged JAR file:
    ```bash
    java -jar target/product-price-0.0.1-SNAPSHOT.jar
    ```

The service will start and be accessible at `http://localhost:8080`. The H2 console can be accessed at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`).

---

## How to Test

The project includes a suite of integration and unit tests.

The **Integration Tests** (`PriceControllerIntegrationTest.java`) use **MockMvc** to simulate HTTP requests to the controller layer without needing to start a full HTTP server. This efficiently validates the complete flow, from the API entry point to the database query and price prioritization logic, against the 5 scenarios specified in the requirements.

To run the tests, execute the following Maven command from the project's root directory:

```bash
./mvnw test