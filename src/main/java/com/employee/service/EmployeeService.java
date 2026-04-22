package com.employee.service;


import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(employee.getName());
            existing.setRole(employee.getRole());
            existing.setSalary(employee.getSalary());
            return repository.save(existing);
        }

        return null;
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}