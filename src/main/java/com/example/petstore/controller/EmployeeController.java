package com.example.petstore.controller;

import com.example.petstore.exception.MyExceptionHandler;
import com.example.petstore.model.Employee;
import com.example.petstore.service.EmployeeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the controller layer for the Employee entity.
 */
@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
@MyExceptionHandler
public class EmployeeController {
  private final EmployeeService service;

  @GetMapping
  public List<Employee> getEmployees() {
    return service.getEmployees();
  }

  @GetMapping("/findEmployeeByFirstNameOrRole")
  public List<Employee> getEmployeesByFirstNameAndRole(@RequestParam(defaultValue = "User")
                                                         String name,
                                                       @RequestParam(required = false,
                                                           defaultValue = "User") String role) {
    return service.findEmployeesByFirstNameAndRole(name, role);
  }

  @PostMapping("saveEmployee")
  public Employee saveEmployee(@RequestBody @Valid Employee employee) {
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
