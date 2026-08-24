package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    
    private final EmployeeRepository employeeRepository;
    
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            if (employeeRepository.count() == 0) {
                Employee emp1 = new Employee();
                emp1.setFirstName("John");
                emp1.setLastName("Doe");
                emp1.setEmail("john.doe@example.com");
                emp1.setPhoneNumber("9876543210");
                emp1.setDepartment("Engineering");
                emp1.setPosition("Senior Software Engineer");
                emp1.setAddress("123 Main St");
                emp1.setCity("New York");
                emp1.setState("NY");
                emp1.setZipCode("10001");
                emp1.setSalary(120000.0);
                emp1.setIsActive(true);
                
                Employee emp2 = new Employee();
                emp2.setFirstName("Jane");
                emp2.setLastName("Smith");
                emp2.setEmail("jane.smith@example.com");
                emp2.setPhoneNumber("9876543211");
                emp2.setDepartment("Human Resources");
                emp2.setPosition("HR Manager");
                emp2.setAddress("456 Oak Ave");
                emp2.setCity("Boston");
                emp2.setState("MA");
                emp2.setZipCode("02101");
                emp2.setSalary(95000.0);
                emp2.setIsActive(true);
                
                Employee emp3 = new Employee();
                emp3.setFirstName("Michael");
                emp3.setLastName("Johnson");
                emp3.setEmail("michael.johnson@example.com");
                emp3.setPhoneNumber("9876543212");
                emp3.setDepartment("Engineering");
                emp3.setPosition("Junior Developer");
                emp3.setAddress("789 Pine Rd");
                emp3.setCity("Chicago");
                emp3.setState("IL");
                emp3.setZipCode("60601");
                emp3.setSalary(75000.0);
                emp3.setIsActive(true);
                
                employeeRepository.save(emp1);
                employeeRepository.save(emp2);
                employeeRepository.save(emp3);
                
                System.out.println("Sample data initialized successfully");
            }
        };
    }
}
