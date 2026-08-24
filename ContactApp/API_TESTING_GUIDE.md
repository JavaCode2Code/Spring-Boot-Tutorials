# Employee Contact App - API Testing Guide

## Quick Start

### 1. Build the Application
```bash
cd C:\Users\Sateesh\IdeaProjects\ContactApp
mvn clean install
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Access Resources
- **API Base URL:** http://localhost:8080/api/employees
- **H2 Console:** http://localhost:8080/h2-console

## Sample Requests Using curl

### 1. Create a New Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d "{
    \"firstName\": \"Alice\",
    \"lastName\": \"Brown\",
    \"email\": \"alice.brown@example.com\",
    \"phoneNumber\": \"9876543214\",
    \"department\": \"Finance\",
    \"position\": \"Financial Analyst\",
    \"address\": \"321 Elm St\",
    \"city\": \"Los Angeles\",
    \"state\": \"CA\",
    \"zipCode\": \"90001\",
    \"salary\": 85000.0
  }"
```

### 2. Get All Employees
```bash
curl http://localhost:8080/api/employees
```

### 3. Get Employee by ID
```bash
curl http://localhost:8080/api/employees/1
```

### 4. Update an Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d "{
    \"firstName\": \"John\",
    \"lastName\": \"Updated\",
    \"email\": \"john.updated@example.com\",
    \"phoneNumber\": \"9876543210\",
    \"department\": \"Engineering\",
    \"position\": \"Lead Engineer\",
    \"address\": \"789 Pine Rd\",
    \"city\": \"San Francisco\",
    \"state\": \"CA\",
    \"zipCode\": \"94102\",
    \"salary\": 140000.0
  }"
```

### 5. Delete an Employee
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

### 6. Search Employees by Name
```bash
curl "http://localhost:8080/api/employees/search?name=John"
```

### 7. Get Employees by Department
```bash
curl http://localhost:8080/api/employees/department/Engineering
```

### 8. Get Employees by Position
```bash
curl http://localhost:8080/api/employees/position/Junior%20Developer
```

### 9. Get Active Employees
```bash
curl http://localhost:8080/api/employees/active
```

### 10. Get Employee by Email
```bash
curl http://localhost:8080/api/employees/email/john.doe@example.com
```

### 11. Deactivate an Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1/deactivate
```

### 12. Activate an Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1/activate
```

## Sample Test Cases

### Test Case 1: Create Employee with Valid Data
**Expected Result:** Employee created successfully with status 201
```json
Response: {
  "id": 4,
  "firstName": "Alice",
  "lastName": "Brown",
  "email": "alice.brown@example.com",
  "phoneNumber": "9876543214",
  "department": "Finance",
  "position": "Financial Analyst",
  "address": "321 Elm St",
  "city": "Los Angeles",
  "state": "CA",
  "zipCode": "90001",
  "salary": 85000.0,
  "isActive": true
}
```

### Test Case 2: Create Employee with Invalid Email
**Expected Result:** Validation error with status 400
```json
Response: {
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid"
  }
}
```

### Test Case 3: Create Employee with Invalid Phone Number
**Expected Result:** Validation error with status 400
```json
Response: {
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "phoneNumber": "Phone number must be 10 digits"
  }
}
```

### Test Case 4: Create Employee with Duplicate Email
**Expected Result:** Error with status 500
```json
Response: {
  "status": 500,
  "message": "An error occurred: Email already exists"
}
```

### Test Case 5: Get Non-existent Employee
**Expected Result:** Not found error with status 404
```json
Response: {
  "status": 404,
  "message": "Employee not found with id: 999"
}
```

### Test Case 6: Update Employee
**Expected Result:** Updated employee returned with status 200
```json
Response: {
  "id": 1,
  "firstName": "John",
  "lastName": "Updated",
  "email": "john.updated@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "position": "Lead Engineer",
  "address": "789 Pine Rd",
  "city": "San Francisco",
  "state": "CA",
  "zipCode": "94102",
  "salary": 140000.0,
  "isActive": true
}
```

### Test Case 7: Deactivate Employee
**Expected Result:** Success message with status 200
```json
Response: {
  "message": "Employee deactivated successfully"
}
```

### Test Case 8: Get Employees by Department
**Expected Result:** List of employees in Engineering department
```json
Response: [
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
  },
  {
    "id": 3,
    "firstName": "Michael",
    "lastName": "Johnson",
    "email": "michael.johnson@example.com",
    "phoneNumber": "9876543212",
    "department": "Engineering",
    "position": "Junior Developer",
    "address": "789 Pine Rd",
    "city": "Chicago",
    "state": "IL",
    "zipCode": "60601",
    "salary": 75000.0,
    "isActive": true
  }
]
```

### Test Case 9: Search by Name
**Expected Result:** List of employees matching search criteria
```bash
Search for "John" returns employees with first/last name containing "John"
```

### Test Case 10: Delete Employee
**Expected Result:** Success message with status 200
```json
Response: {
  "message": "Employee deleted successfully"
}
```

## Default Sample Data

The application comes with 3 sample employees:

1. **John Doe**
   - Email: john.doe@example.com
   - Phone: 9876543210
   - Department: Engineering
   - Position: Senior Software Engineer
   - Salary: $120,000

2. **Jane Smith**
   - Email: jane.smith@example.com
   - Phone: 9876543211
   - Department: Human Resources
   - Position: HR Manager
   - Salary: $95,000

3. **Michael Johnson**
   - Email: michael.johnson@example.com
   - Phone: 9876543212
   - Department: Engineering
   - Position: Junior Developer
   - Salary: $75,000

## Validation Rules Checklist

- [x] First Name: Non-blank, required
- [x] Last Name: Non-blank, required
- [x] Email: Valid format, unique, required
- [x] Phone Number: 10 digits, required
- [x] Department: Non-blank, required
- [x] Position: Non-blank, required
- [x] Address: Optional, max 500 chars
- [x] City: Optional, max 50 chars
- [x] State: Optional, max 50 chars
- [x] Zip Code: Optional, max 10 chars
- [x] Salary: Optional, decimal value

## Performance Tips

1. Use specific queries (by department, position, email) instead of fetching all employees
2. H2 in-memory database is suitable for development/testing
3. For production, switch to MySQL, PostgreSQL, or other databases
4. Enable pagination for large datasets
5. Consider caching frequently accessed data

## Troubleshooting

### Port Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### Database Connection Issues
Check H2 console connection string in application.properties

### Validation Errors
- Email: Must be in format `user@example.com`
- Phone: Must be exactly 10 digits
- All required fields must be provided

## Next Steps

1. Test all endpoints using curl or Postman
2. Access H2 console to view database tables
3. Add additional features as needed
4. Deploy to production environment
5. Integrate with frontend application

## Files Generated

- ✅ Entity: Employee.java
- ✅ Repository: EmployeeRepository.java
- ✅ Service: EmployeeService.java
- ✅ Controller: EmployeeController.java
- ✅ DTO: EmployeeDTO.java
- ✅ Exception Handling: ResourceNotFoundException.java, GlobalExceptionHandler.java
- ✅ Configuration: WebConfig.java, DataInitializer.java
- ✅ Properties: application.properties
- ✅ Test: AppTest.java
- ✅ Documentation: README.md
- ✅ Build: pom.xml
