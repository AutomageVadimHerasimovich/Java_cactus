package com.example.petstore.controller;

import com.example.petstore.model.Employee;
import com.example.petstore.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    @GetMapping
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }
    @GetMapping("/findEmployeeByFirstNameOrRole")
    public List<Employee> getEmployeesByFirstNameAndRole(@RequestParam(defaultValue = "User") String name, @RequestParam(required = false, defaultValue = "User") String role) {
        return service.findEmployeesByFirstNameAndRole(name, role);
    }
    @PostMapping("saveEmployee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }
    @GetMapping("/{phone}")
    public Employee getEmployeeByPhone(@PathVariable String phone) {
        return service.getEmployeeByPhone(phone);
    }
    @PutMapping("updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }
    @DeleteMapping("/deleteEmployee/{phone}")
    public void deleteEmployee(@PathVariable String phone) {
        service.deleteEmployee(phone);
    }
}
