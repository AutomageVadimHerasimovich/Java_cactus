package com.example.petstore.service;

import com.example.petstore.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeByPhone(String phone);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String phone);
}
