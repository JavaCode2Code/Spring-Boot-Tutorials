package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        Employee employee = new Employee();
        mapDTOToEntity(employeeDTO, employee);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapEntityToDTO(savedEmployee);
    }
    
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        
        if (!employee.getEmail().equals(employeeDTO.getEmail()) &&
                employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        mapDTOToEntity(employeeDTO, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        return mapEntityToDTO(updatedEmployee);
    }
    
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapEntityToDTO(employee);
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }
    
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
    }
    
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "email", email));
        return mapEntityToDTO(employee);
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getActiveEmployees() {
        return employeeRepository.findByIsActive(true)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeDTO> searchEmployeesByName(String name) {
        return employeeRepository.searchByName(name)
                .stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }
    
    public void deactivateEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setIsActive(false);
        employeeRepository.save(employee);
    }
    
    public void activateEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employee.setIsActive(true);
        employeeRepository.save(employee);
    }
    
    private void mapDTOToEntity(EmployeeDTO dto, Employee entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDepartment(dto.getDepartment());
        entity.setPosition(dto.getPosition());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());
        entity.setSalary(dto.getSalary());
        if (dto.getIsActive() != null) {
            entity.setIsActive(dto.getIsActive());
        }
    }
    
    private EmployeeDTO mapEntityToDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setDepartment(entity.getDepartment());
        dto.setPosition(entity.getPosition());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());
        dto.setSalary(entity.getSalary());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }
}
