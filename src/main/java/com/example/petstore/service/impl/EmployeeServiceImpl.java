package com.example.petstore.service.impl;

import com.example.petstore.aspect.Logged;
import com.example.petstore.model.Employee;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.service.EmployeeService;
import com.example.petstore.service.cache.EmployeeCache;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service class for the Employee entity.
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  private final EmployeeCache employeeCache;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCache employeeCache) {
    this.employeeRepository = employeeRepository;
    this.employeeCache = employeeCache;
  }

  public List<Employee> getEmployees() {
    log.info("Getting all employees");
    return employeeRepository.findAll();
  }

  /**
   * Saves the given employee.
   *
   * @param employee the employee to save
   * @return the saved employee
   */
  @Logged
  public Employee saveEmployee(Employee employee) {
    employeeRepository.save(employee);
    employeeCache.put(employee.getPhone(), employee);
    log.info("Employee saved: {}", employee);
    return employee;
  }

  /**
   * Gets the employee with the given phone.
   *
   * @param phone the phone of the employee to get
   * @return the employee with the given phone
   */
  @Logged
  public Employee getEmployeeByPhone(String phone) {
    employeeCache.get(phone);
    log.info("Getting employee by phone: {}", phone);
    log.info("Employee found: {}", employeeRepository.findEmployeeByPhone(phone));
    return employeeRepository.findEmployeeByPhone(phone);
  }

  /**
   * Updates the given employee.
   *
   * @param employee the employee to update
   * @return the updated employee
   */

  @Logged
  public Employee updateEmployee(Employee employee) {
    Employee existingEmployee = employeeRepository.findEmployeeByPhone(employee.getPhone());
    if (existingEmployee != null) {
      existingEmployee.setFirstName(employee.getFirstName());
      existingEmployee.setRole(employee.getRole());
      existingEmployee.setPassword(employee.getPassword());
      employeeCache.put(existingEmployee.getPhone(), existingEmployee);
    }
    assert existingEmployee != null;
    log.info("Employee updated: {}", existingEmployee);
    return employeeRepository.save(existingEmployee);
  }

  @Logged
  public List<Employee> findEmployeesByFirstNameAndRole(String name, String role) {
    log.info("Finding employees by first name and role: {} {}", name, role);
    return employeeRepository.findEmployeesByFirstNameAndRole(name, role);
  }

  /**
   * Deletes the employee with the given phone.
   *
   * @param phone the phone of the employee to delete
   */
  @Logged
  @Transactional
  public void deleteEmployee(String phone) {
    employeeRepository.deleteEmployeeByPhone(phone);
    employeeCache.evict(phone);
    log.info("Employee deleted by phone: {}", phone);
  }
}
