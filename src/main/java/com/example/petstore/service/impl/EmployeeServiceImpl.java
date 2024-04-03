package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.service.EmployeeService;
import com.example.petstore.service.cache.EmployeeCache;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service class for the Employee entity.
 */
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

  /**
   * Saves the given employee.
   *
   * @param employee the employee to save
   * @return the saved employee
   */
  public Employee saveEmployee(Employee employee) {
    employeeRepository.save(employee);
    employeeCache.put(employee.getPhone(), employee);
    return employee;
  }

  public Employee getEmployeeByPhone(String phone) {
    return employeeCache.get(phone);
  }

  /**
   * Updates the given employee.
   *
   * @param employee the employee to update
   * @return the updated employee
   */
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
  public void deleteEmployee(String phone) {
    employeeRepository.deleteEmployeeByPhone(phone);
    employeeCache.evict(phone);
  }
}
