package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
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
        Pet existingPet = petRepository.findPetByPhone(employee.getPhone());
        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setPet(existingPet);
        }
        assert existingEmployee != null;
        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(String phone) {
        employeeRepository.deleteEmployeeByPhone(phone);
    }
}
