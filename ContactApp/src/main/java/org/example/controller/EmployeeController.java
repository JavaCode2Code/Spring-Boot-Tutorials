package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeDTO;
import org.example.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO created = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updated = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable String email) {
        EmployeeDTO employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }
    
    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable String department) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/position/{position}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByPosition(@PathVariable String position) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByPosition(position);
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<EmployeeDTO>> getActiveEmployees() {
        List<EmployeeDTO> employees = employeeService.getActiveEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployeesByName(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.searchEmployeesByName(name);
        return ResponseEntity.ok(employees);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, String>> deactivateEmployee(@PathVariable Long id) {
        employeeService.deactivateEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deactivated successfully");
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}/activate")
    public ResponseEntity<Map<String, String>> activateEmployee(@PathVariable Long id) {
        employeeService.activateEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee activated successfully");
        return ResponseEntity.ok(response);
    }
}
