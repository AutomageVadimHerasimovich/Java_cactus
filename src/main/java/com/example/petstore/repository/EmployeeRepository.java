package com.example.petstore.repository;

import com.example.petstore.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This interface represents the repository layer for the Employee entity.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Employee findEmployeeByPhone(String phone);

  void deleteEmployeeByPhone(String phone);

  @Query(value = "SELECT * FROM employees WHERE first_name = :name AND role = :role",
          nativeQuery = true)
  List<Employee> findEmployeesByFirstNameAndRole(@Param("name") String firstName,
                                                 @Param("role") String role);
}
