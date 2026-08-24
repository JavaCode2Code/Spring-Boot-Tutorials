package org.example.repository;

import org.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Optional<Employee> findByEmail(String email);
    
    List<Employee> findByDepartment(String department);
    
    List<Employee> findByPosition(String position);
    
    List<Employee> findByIsActive(Boolean isActive);
    
    @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE %:name%")
    List<Employee> searchByName(@Param("name") String name);
    
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:firstName% OR e.lastName LIKE %:lastName%")
    List<Employee> findByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
