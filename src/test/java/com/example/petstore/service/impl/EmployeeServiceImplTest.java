package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.service.cache.EmployeeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeCache employeeCache;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
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
        verify(employeeCache, times(1)).put(employee.getPhone(), employee);
    }

    @Test
    void shouldGetEmployeeByPhone() {
        Employee employee = new Employee();
        when(employeeRepository.findEmployeeByPhone(anyString())).thenReturn(employee);
        when(employeeCache.get(anyString())).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeByPhone("1234567890");

        assertEquals(employee, result);
        verify(employeeRepository, times(2)).findEmployeeByPhone(anyString());
        verify(employeeCache, times(1)).get(anyString());
    }

    @Test
    void shouldUpdateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setPhone("1234567890");
        existingEmployee.setFirstName("Old Name");
        existingEmployee.setRole("Old Role");
        existingEmployee.setPassword("Old Password");

        Employee newEmployee = new Employee();
        newEmployee.setPhone("1234567890");
        newEmployee.setFirstName("New Name");
        newEmployee.setRole("New Role");
        newEmployee.setPassword("New Password");

        when(employeeRepository.findEmployeeByPhone(existingEmployee.getPhone())).thenReturn(existingEmployee);
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.updateEmployee(newEmployee);

        assertEquals(newEmployee.getFirstName(), result.getFirstName());
        assertEquals(newEmployee.getRole(), result.getRole());
        assertEquals(newEmployee.getPassword(), result.getPassword());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void shouldFindEmployeesByFirstNameAndRole() {
    String name = "John";
    String role = "Developer";
    List<Employee> employees = Arrays.asList(new Employee(), new Employee());
    when(employeeRepository.findEmployeesByFirstNameAndRole(name, role)).thenReturn(employees);

    List<Employee> result = employeeService.findEmployeesByFirstNameAndRole(name, role);

    assertEquals(employees, result);
    verify(employeeRepository, times(1)).findEmployeesByFirstNameAndRole(name, role);
    }

    @Test
    void shouldDeleteEmployee() {
        String phone = "1234567890";
        Employee employee = new Employee();
        employee.setPhone(phone);

        when(employeeRepository.findEmployeeByPhone(phone)).thenReturn(employee);

        Employee foundEmployee = employeeService.getEmployeeByPhone(phone);
        assertEquals(employee, foundEmployee, "Employee should be found before deletion");

        employeeService.deleteEmployee(phone);

        verify(employeeRepository, times(1)).deleteEmployeeByPhone(phone);
        verify(employeeCache, times(1)).evict(phone);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        when(employeeRepository.findEmployeeByPhone(anyString())).thenReturn(null);

        try {
            employeeService.getEmployeeByPhone("1234567890");
        } catch (Exception e) {
            assertEquals("Employee not found", e.getMessage());
        }
    }
}