package com.example.petstore.service.cache;

import com.example.petstore.model.Employee;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * A cache for employees.
 */
@Slf4j
@Component
public class EmployeeCache {

  private static final int MAX_SIZE = 10;

  private final Map<String, Employee> cache = new LinkedHashMap<>();

  /**
   * Puts the given employee into the cache.
   *
   * @param phone the phone number of the employee
   * @param employee the employee to put
   */
  public void put(String phone, Employee employee) {
    cache.put(phone, employee);
    if (cache.size() > MAX_SIZE) {
      String oldestKey = cache.keySet().iterator().next();
      cache.remove(oldestKey);
    }
  }

  public Optional<Employee> get(String phone) {
    return Optional.ofNullable(cache.get(phone));
  }

  public void evict(String phone) {
    cache.remove(phone);
  }
}
