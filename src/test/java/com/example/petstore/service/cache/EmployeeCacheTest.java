package com.example.petstore.service.cache;

import com.example.petstore.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCacheTest {

    private EmployeeCache employeeCache;

    @BeforeEach
    void setUp() {
        employeeCache = new EmployeeCache();
    }

    @Test
    void shouldPutEmployeeIntoCache() {
        Employee employee = new Employee();
        employeeCache.put("1234567890", employee);

        Optional<Employee> result = employeeCache.get("1234567890");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void shouldGetEmployeeFromCache() {
        Employee employee = new Employee();
        employeeCache.put("1234567890", employee);

        Optional<Employee> result = employeeCache.get("1234567890");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalIfEmployeeNotInCache() {
        Optional<Employee> result = employeeCache.get("1234567890");

        assertFalse(result.isPresent());
    }

    @Test
    void shouldEvictEmployeeFromCache() {
        Employee employee = new Employee();
        employeeCache.put("1234567890", employee);
        employeeCache.evict("1234567890");

        Optional<Employee> result = employeeCache.get("1234567890");

        assertFalse(result.isPresent());
    }
}