# Microservices Book and Borrowing System

This project comprises two microservices: **Book Service** and **Borrowing Service**, designed to manage a library's book inventory and the borrowing process, respectively. Built with Spring Boot, these services communicate over REST APIs, with service discovery facilitated by Eureka. The system also utilizes OpenFeign for declarative REST client creation, enabling the Borrowing Service to communicate seamlessly with the Book Service to update book statuses based on borrowing actions.

## System Architecture

The system architecture is based on microservices principles, allowing for scalability, flexibility, and the independent deployment of services. The **Book Service** manages book inventory, including details such as book titles, authors, and availability status. The **Borrowing Service** handles operations related to borrowing books, tracking which user has borrowed which book and the duration of the borrow.

### Service Discovery

Service discovery is implemented using **Eureka**, which enables each service to discover and communicate with other services without hardcoding their URLs. This is especially crucial in cloud environments where service instances might dynamically change.

### Communication

Services communicate using RESTful APIs, with **OpenFeign** simplifying the process of calling REST endpoints between services. For instance, when a book is borrowed, the Borrowing Service uses OpenFeign to call the Book Service to update the book's status to "BORROWED". This decouples the services by allowing them to only share APIs and not direct code dependencies.

## System Requirements

- Java JDK 11 or later
- Maven 3.6 or later
- An IDE such as IntelliJ IDEA or Eclipse

## Setup and Running Services

### Eureka Discovery Service

1. Navigate to the root directory of the Eureka server.
2. Build the application using Maven: `mvn clean install`.
3. Run the application: `java -jar target/eureka-server-0.0.1-SNAPSHOT.jar`.
4. Access the Eureka dashboard at `http://localhost:8761` to confirm it's running.

### Book Service

1. Navigate to the root directory of the Book Service.
2. Build the application: `mvn clean install`.
3. Run the application: `java -jar target/book-service-0.0.1-SNAPSHOT.jar`.
4. The service registers with Eureka and is accessible on port 8081.

### Borrowing Service

1. Navigate to the root directory of the Borrowing Service.
2. Build the application: `mvn clean install`.
3. Run the application: `java -jar target/borrowing-service-0.0.1-SNAPSHOT.jar`.
4. The service registers with Eureka and is accessible on port 8082.

## API Documentation

### Book Service Endpoints

- **GET /books**: Retrieve a list of all books.
- **GET /books/{id}**: Retrieve details of a specific book by its ID.
- **POST /books**: Add a new book. Requires a JSON body with title, author, and status.
- **PUT /books/{id}/status**: Update the status of a book. Requires a JSON body with the new status.
- **DELETE /books/{id}**: Delete a specific book by its ID.

### Borrowing Service Endpoints

- **POST /borrows/borrow**: Borrow a book. Requires query parameters `bookId` and `userId`.
- **POST /borrows/return/{borrowId}**: Return a borrowed book using its borrow record ID.
- **GET /borrows**: Retrieve a list of all borrow records.

## Testing with Postman

- Import the Postman collection provided with the project or manually configure requests according to the API documentation.
- Set up environment variables in Postman for `book-service` and `borrowing-service` URLs.
- Send requests to test the endpoints. Ensure both the Book Service and Borrowing Service are running and have registered with Eureka.

## Troubleshooting

- If services fail to register with Eureka, ensure Eureka is running and check application properties for correct Eureka client configuration.
- For database-related issues, verify the H2 console for the Book Service and ensure the correct JDBC URL is configured.
