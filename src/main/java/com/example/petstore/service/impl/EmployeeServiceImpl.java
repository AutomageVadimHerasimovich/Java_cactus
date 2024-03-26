package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.service.EmployeeService;
import com.example.petstore.service.cache.EmployeeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCache employeeCache;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCache employeeCache) {
        this.employeeRepository = employeeRepository;
        this.employeeCache = employeeCache;
    }
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    //@Cacheable(value = "employees")
    public Employee saveEmployee(Employee employee) {
        //return
        employeeRepository.save(employee);
        employeeCache.put(employee.getPhone(), employee);
        return employee;
    }
    //@Cacheable(value = "employees", key = "#phone")
    public Employee getEmployeeByPhone(String phone) {
        //return employeeRepository.findEmployeeByPhone(phone);
        return employeeCache.get(phone);
    }
    //@CachePut(value = "employees", key = "#employee.phone")
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findEmployeeByPhone(employee.getPhone());
        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setPassword(employee.getPassword());
            employeeCache.put(existingEmployee.getPhone(), existingEmployee);
        }
        assert existingEmployee != null;
        return employeeRepository.save(existingEmployee);
    }

    public List<Employee> findEmployeesByFirstNameAndRole(String name, String role) {
        return employeeRepository.findEmployeesByFirstNameAndRole(name, role);
    }

    @Transactional
    //@CacheEvict(value = "employees", key = "#phone")
    public void deleteEmployee(String phone) {
        employeeRepository.deleteEmployeeByPhone(phone);
        employeeCache.evict(phone);
    }
}
