package com.example.petstore.repository;

import com.example.petstore.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByPhone(String phone);

    void deleteEmployeeByPhone(String phone);
    @Query("SELECT e FROM Employee e WHERE e.firstName = :name AND e.role = :role")
    List<Employee> findEmployeesByFirstNameAndRole(@Param("name") String firstName, @Param("role") String role);
}
