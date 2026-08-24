# Employee Contact Management Application

A comprehensive Spring Boot application for managing employee contact information with REST APIs.

## Features

- ✅ Create, Read, Update, Delete (CRUD) operations for employees
- ✅ Search employees by name, email, department, or position
- ✅ Activate/Deactivate employee status
- ✅ Input validation and error handling
- ✅ CORS support for cross-origin requests
- ✅ H2 in-memory database with sample data
- ✅ RESTful API design
- ✅ Comprehensive exception handling
- ✅ Transactional support

## Technology Stack

- **Framework:** Spring Boot 3.1.5
- **Java Version:** 17
- **Database:** H2 (in-memory)
- **ORM:** Spring Data JPA/Hibernate
- **Build Tool:** Maven
- **API Documentation:** REST

## Project Structure

```
ContactApp/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── App.java                    # Main application class
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── EmployeeService.java   # Business logic
│   │   │   ├── repository/
│   │   │   │   └── EmployeeRepository.java # Database queries
│   │   │   ├── entity/
│   │   │   │   └── Employee.java          # JPA entity
│   │   │   ├── dto/
│   │   │   │   └── EmployeeDTO.java       # Data transfer object
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── config/
│   │   │       ├── WebConfig.java         # CORS configuration
│   │   │       └── DataInitializer.java   # Sample data initialization
│   │   └── resources/
│   │       └── application.properties     # Configuration
│   └── test/
└── pom.xml                                 # Maven configuration
```

## Employee Entity Fields

- **id:** Unique identifier (auto-generated)
- **firstName:** Employee's first name (required)
- **lastName:** Employee's last name (required)
- **email:** Email address (required, unique)
- **phoneNumber:** 10-digit phone number (required)
- **department:** Department name (required)
- **position:** Job position (required)
- **address:** Street address (optional)
- **city:** City (optional)
- **state:** State (optional)
- **zipCode:** Zip code (optional)
- **salary:** Annual salary (optional)
- **isActive:** Active status (default: true)

## REST API Endpoints

### Create Employee
```
POST /api/employees
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "position": "Senior Developer",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001",
  "salary": 120000.0
}
```

### Get Employee by ID
```
GET /api/employees/{id}
```

### Get All Employees
```
GET /api/employees
```

### Update Employee
```
PUT /api/employees/{id}
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "position": "Lead Developer",
  "address": "456 Oak Ave",
  "city": "Boston",
  "state": "MA",
  "zipCode": "02101",
  "salary": 130000.0
}
```

### Delete Employee
```
DELETE /api/employees/{id}
```

### Get Employee by Email
```
GET /api/employees/email/{email}
```

### Get Employees by Department
```
GET /api/employees/department/{department}
```

### Get Employees by Position
```
GET /api/employees/position/{position}
```

### Get Active Employees
```
GET /api/employees/active
```

### Search Employees by Name
```
GET /api/employees/search?name={name}
```

### Deactivate Employee
```
PUT /api/employees/{id}/deactivate
```

### Activate Employee
```
PUT /api/employees/{id}/activate
```

## Installation & Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build the Application
```bash
cd ContactApp
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access H2 Console
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:employeedb
Username: sa
Password: (leave blank)
```

## Sample Data

The application initializes with 3 sample employees:
1. John Doe - Senior Software Engineer (Engineering)
2. Jane Smith - HR Manager (Human Resources)
3. Michael Johnson - Junior Developer (Engineering)

## API Response Format

### Success Response
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "position": "Senior Developer",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001",
  "salary": 120000.0,
  "isActive": true
}
```

### Error Response
```json
{
  "status": 404,
  "message": "Employee not found with id: 999"
}
```

### Validation Error Response
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid",
    "phoneNumber": "Phone number must be 10 digits"
  }
}
```

## Validation Rules

- **firstName:** Required, non-blank
- **lastName:** Required, non-blank
- **email:** Required, valid email format, must be unique
- **phoneNumber:** Required, exactly 10 digits
- **department:** Required, non-blank
- **position:** Required, non-blank
- **address:** Optional, max 500 characters
- **city:** Optional, max 50 characters
- **state:** Optional, max 50 characters
- **zipCode:** Optional, max 10 characters
- **salary:** Optional, decimal value

## Configuration

All application settings are in `application.properties`:

```properties
# Server Port
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:employeedb
spring.datasource.username=sa

# Hibernate
spring.jpa.hibernate.ddl-auto=update

# H2 Console
spring.h2.console.enabled=true
```

## Testing

### Using curl

```bash
# Create employee
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Sarah",
    "lastName": "Wilson",
    "email": "sarah.wilson@example.com",
    "phoneNumber": "9876543213",
    "department": "Sales",
    "position": "Sales Manager",
    "salary": 100000.0
  }'

# Get all employees
curl http://localhost:8080/api/employees

# Get employee by ID
curl http://localhost:8080/api/employees/1

# Search by name
curl "http://localhost:8080/api/employees/search?name=John"

# Get by department
curl http://localhost:8080/api/employees/department/Engineering
```

### Using Postman

1. Import the REST API endpoints into Postman
2. Create a collection with the endpoints listed above
3. Test each endpoint with sample data

## Exception Handling

The application includes comprehensive exception handling:

- **ResourceNotFoundException:** Thrown when an employee is not found
- **MethodArgumentNotValidException:** Validation errors for invalid input
- **Duplicate Email:** Prevents duplicate email addresses
- **Global Exception Handler:** Catches all unhandled exceptions

## Future Enhancements

- Add Spring Security for authentication/authorization
- Implement pagination and sorting
- Add audit logging
- Create unit and integration tests
- Add API documentation with Swagger/SpringFox
- Implement caching with Spring Cache
- Add file upload/export functionality
- Create Angular/React frontend

## License

This project is open source and available for educational purposes.

## Support

For issues and questions, please refer to the Spring Boot documentation or contact the development team.
