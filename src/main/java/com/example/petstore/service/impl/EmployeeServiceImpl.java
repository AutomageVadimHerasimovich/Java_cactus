package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByPhone(String phone) {
        return employeeRepository.findEmployeeByPhone(phone);
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findEmployeeByPhone(employee.getPhone());
        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setPassword(employee.getPassword());
        }
        assert existingEmployee != null;
        return employeeRepository.save(existingEmployee);
    }

    public List<Employee> findEmployeesByFirstNameAndRole(String name, String role) {
        return employeeRepository.findEmployeesByFirstNameAndRole(name, role);
    }

    @Transactional
    public void deleteEmployee(String phone) {
        employeeRepository.deleteEmployeeByPhone(phone);
    }
}
