package com.example.petstore.service.cache;

import com.example.petstore.model.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class EmployeeCache {

    private final Map<String, Employee> cache = new HashMap<>();

    public void put(String phone, Employee employee) {
        cache.put(phone, employee);
    }

    public Employee get(String phone) {
        return cache.get(phone);
    }

    public void evict(String phone) {
        cache.remove(phone);
    }
}
