package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.service.cache.EmployeeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

  @Mock
  EmployeeRepository employeeRepository;

  @Mock
  EmployeeCache employeeCache;

  @InjectMocks
  EmployeeServiceImpl employeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldGetAllEmployees() {
    List<Employee> employees = Arrays.asList(new Employee(), new Employee());
    when(employeeRepository.findAll()).thenReturn(employees);

    List<Employee> result = employeeService.getEmployees();

    assertEquals(employees, result);
    verify(employeeRepository, times(1)).findAll();
  }

  @Test
  void shouldSaveEmployee() {
    Employee employee = new Employee();
    when(employeeRepository.save(employee)).thenReturn(employee);

    Employee result = employeeService.saveEmployee(employee);

    assertEquals(employee, result);
    verify(employeeRepository, times(1)).save(employee);
    verify(employeeCache, times(1)).put(anyString(), any(Employee.class));
  }

  @Test
  void shouldGetEmployeeByPhone() {
    Employee employee = new Employee();
    when(employeeRepository.findEmployeeByPhone(anyString())).thenReturn(employee);
    when(employeeCache.get(anyString())).thenReturn(Optional.of(employee));

    Employee result = employeeService.getEmployeeByPhone("1234567890");

    assertEquals(employee, result);
    verify(employeeRepository, times(1)).findEmployeeByPhone(anyString());
    verify(employeeCache, times(1)).get(anyString());
  }

  // Add more tests for other methods in the EmployeeServiceImpl class
}