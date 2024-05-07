package com.example.petstore.controller;

import com.example.petstore.model.Employee;
import com.example.petstore.service.CounterService;
import com.example.petstore.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @SuppressWarnings("unused")
    @Mock
    CounterService counterService;

    @InjectMocks
    EmployeeController employeeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testGetEmployees() throws Exception {
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(new Employee(), new Employee()));
        mockMvc.perform(get("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeesByFirstNameAndRole() throws Exception {
        when(employeeService.findEmployeesByFirstNameAndRole(anyString(), anyString())).thenReturn(Arrays.asList(new Employee(), new Employee()));
        mockMvc.perform(get("/api/v1/employee/findEmployeeByFirstNameOrRole")
                .param("name", "User")
                .param("role", "User")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveEmployee() throws Exception {
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(new Employee());
        mockMvc.perform(post("/api/v1/employee/saveEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeeByPhone() throws Exception {
        when(employeeService.getEmployeeByPhone(anyString())).thenReturn(new Employee());
        mockMvc.perform(get("/api/v1/employee/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(new Employee());
        mockMvc.perform(put("/api/v1/employee/updateEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/v1/employee/deleteEmployee/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}