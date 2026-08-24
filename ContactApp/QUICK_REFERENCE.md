# Employee Contact App - Quick Reference Guide

## 🚀 Quick Start (Copy & Paste)

### 1. Build Project
```bash
cd C:\Users\Sateesh\IdeaProjects\ContactApp
mvn clean install
```

### 2. Run Application
```bash
mvn spring-boot:run
```

### 3. Access Application
- API: http://localhost:8080/api/employees
- H2 Console: http://localhost:8080/h2-console

---

## 📌 Most Used API Endpoints

### Get All Employees
```
GET /api/employees
```

### Get Employee by ID
```
GET /api/employees/1
```

### Create Employee
```
POST /api/employees
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "position": "Developer",
  "salary": 100000.0
}
```

### Update Employee
```
PUT /api/employees/1
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "department": "Engineering",
  "position": "Senior Developer",
  "salary": 120000.0
}
```

### Delete Employee
```
DELETE /api/employees/1
```

### Search by Department
```
GET /api/employees/department/Engineering
```

### Deactivate Employee
```
PUT /api/employees/1/deactivate
```

---

## 🗂️ File Locations

| Component | Path |
|-----------|------|
| Main App | src/main/java/org/example/App.java |
| Entity | src/main/java/org/example/entity/Employee.java |
| Service | src/main/java/org/example/service/EmployeeService.java |
| Controller | src/main/java/org/example/controller/EmployeeController.java |
| Repository | src/main/java/org/example/repository/EmployeeRepository.java |
| Properties | src/main/resources/application.properties |
| Build | pom.xml |

---

## 📋 Project Statistics

- **Java Files**: 10
- **REST Endpoints**: 14
- **Database Tables**: 1
- **Sample Records**: 3
- **Validations**: 6
- **Custom Queries**: 6
- **Build Status**: ✅ SUCCESS
- **Test Status**: ✅ PASSED

---

## 🔧 Configuration Changes

### Change Port
Edit `application.properties`:
```properties
server.port=8081
```

### Use MySQL Instead of H2
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```

### Enable SQL Logging
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 📊 Database Info

**H2 Console Access:**
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:employeedb
- Username: sa
- Password: (blank)

**Table Name**: employees

**Columns**:
```
id, first_name, last_name, email, phone_number,
department, position, address, city, state,
zip_code, salary, is_active
```

---

## ⚠️ Common Validation Errors

| Error | Fix |
|-------|-----|
| Email should be valid | Use format: user@domain.com |
| Phone number must be 10 digits | Use exactly 10 digits, no spaces |
| Email already exists | Use a unique email address |
| Required field missing | Provide all required fields |

---

## 🎯 Common Operations

### Create 5 Employees
```bash
for i in {1..5}; do
  curl -X POST http://localhost:8080/api/employees \
    -H "Content-Type: application/json" \
    -d "{\"firstName\":\"Employee$i\",\"lastName\":\"Test\",\"email\":\"emp$i@example.com\",\"phoneNumber\":\"987654321$i\",\"department\":\"Engineering\",\"position\":\"Developer\",\"salary\":100000.0}"
done
```

### Get Count of Employees
Use H2 Console:
```sql
SELECT COUNT(*) FROM employees;
```

### Find by Department
```bash
curl "http://localhost:8080/api/employees/department/Engineering"
```

### Export All to CSV (Manual)
1. Access H2 Console
2. Run query: SELECT * FROM employees;
3. Export results as CSV

---

## 🐛 Troubleshooting

### Build Fails
```bash
# Clean Maven cache
mvn clean
# Rebuild
mvn install
```

### Port 8080 In Use
```bash
# Change port in application.properties
server.port=8081
```

### H2 Console Won't Load
Check if h2 dependency is in pom.xml

### Validation Errors
Check request JSON format - all required fields present?

### No Sample Data
Restart application - DataInitializer runs on startup

---

## 📦 Maven Commands

```bash
# Build only
mvn clean install -q

# Run tests only
mvn test

# Run application
mvn spring-boot:run

# Skip tests during build
mvn clean install -DskipTests

# Generate JAR
mvn package

# Clean build artifacts
mvn clean
```

---

## 🔗 Integration Examples

### With Angular Frontend
```typescript
import { HttpClient } from '@angular/common/http';

constructor(private http: HttpClient) {}

getEmployees() {
  return this.http.get('http://localhost:8080/api/employees');
}

createEmployee(emp: any) {
  return this.http.post('http://localhost:8080/api/employees', emp);
}
```

### With React Frontend
```javascript
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/employees';

export const getEmployees = () => axios.get(API_URL);
export const getEmployee = (id) => axios.get(`${API_URL}/${id}`);
export const createEmployee = (data) => axios.post(API_URL, data);
export const updateEmployee = (id, data) => axios.put(`${API_URL}/${id}`, data);
export const deleteEmployee = (id) => axios.delete(`${API_URL}/${id}`);
```

---

## 📚 Documentation Files

1. **README.md** - Full project documentation
2. **API_TESTING_GUIDE.md** - Testing examples and curl commands
3. **DEVELOPMENT_SUMMARY.md** - Architecture and features
4. **QUICK_REFERENCE.md** - This file

---

## ⏱️ Project Timeline

- **Setup**: Completed ✅
- **Entity Design**: Completed ✅
- **Repository Layer**: Completed ✅
- **Service Layer**: Completed ✅
- **Controller Layer**: Completed ✅
- **Exception Handling**: Completed ✅
- **Configuration**: Completed ✅
- **Testing**: Completed ✅
- **Documentation**: Completed ✅

---

## 🎓 Key Concepts Demonstrated

1. **Spring Boot Framework**: Auto-configuration, embedded server
2. **REST API**: CRUD operations with HTTP methods
3. **JPA/Hibernate**: Object-relational mapping
4. **Spring Data**: Repository pattern implementation
5. **Validation**: Input validation with annotations
6. **Exception Handling**: Global exception handler
7. **CORS**: Cross-origin resource sharing
8. **Transactions**: Database transaction management

---

## 🌐 Endpoints at a Glance

```
POST   /api/employees                    → Create
GET    /api/employees                    → Get All
GET    /api/employees/{id}               → Get One
PUT    /api/employees/{id}               → Update
DELETE /api/employees/{id}               → Delete
GET    /api/employees/email/{email}      → By Email
GET    /api/employees/department/{dept}  → By Department
GET    /api/employees/position/{pos}     → By Position
GET    /api/employees/active             → Active Only
GET    /api/employees/search?name={name} → Search
PUT    /api/employees/{id}/activate      → Activate
PUT    /api/employees/{id}/deactivate    → Deactivate
GET    /h2-console                       → DB Console
```

---

## ✨ Tips & Tricks

1. **Use Postman** - Import all endpoints for easy testing
2. **Check H2 Console** - Verify data directly in database
3. **Read Logs** - Application logs show all details
4. **Validate Input** - Always provide required fields
5. **Use Search** - Faster than fetching all records

---

## 📞 Support Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- Spring Data JPA: https://spring.io/projects/spring-data-jpa
- Lombok Documentation: https://projectlombok.org
- H2 Database: https://www.h2database.com

---

## ✅ Before Going Live

- [ ] Run all tests
- [ ] Test all endpoints
- [ ] Update configuration for production
- [ ] Change database to MySQL/PostgreSQL
- [ ] Enable Spring Security
- [ ] Setup SSL/HTTPS
- [ ] Configure logging
- [ ] Backup database design
- [ ] Document any custom changes
- [ ] Deploy to server

---

**Last Updated**: 2026-08-24  
**Status**: Production Ready  
**Version**: 1.0.0

**Happy Coding!** 🚀
