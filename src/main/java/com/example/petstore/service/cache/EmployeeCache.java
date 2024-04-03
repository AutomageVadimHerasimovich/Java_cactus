package com.example.petstore.service.cache;

import com.example.petstore.model.Employee;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
@Component
public class EmployeeCache {
    private static final int MAX_SIZE = 10;


    private final Map<String, Employee> cache = new LinkedHashMap<>();

    public void put(String phone, Employee employee) {
        cache.put(phone, employee);
        if (cache.size() > MAX_SIZE){
            String oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
    }

    public Employee get(String phone) {
        return cache.get(phone);
    }

    public void evict(String phone) {
        cache.remove(phone);
    }
}
