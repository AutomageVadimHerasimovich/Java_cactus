package com.example.petstore.repository;

import com.example.petstore.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByPhone(String phone);

    void deleteEmployeeByPhone(String phone);
}
