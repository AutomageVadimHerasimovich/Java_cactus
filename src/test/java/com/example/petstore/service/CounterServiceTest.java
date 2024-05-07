package com.example.petstore.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterServiceTest {

    private CounterService counterService;

    @BeforeEach
    void setUp() {
        counterService = new CounterService();
    }

    @Test
    void testIncrementAndGet() {
        int result = counterService.incrementAndGet();
        assertEquals(1, result, "Counter should be incremented to 1");
    }

    @Test
    void testGet() {
        counterService.incrementAndGet();
        int result = counterService.get();
        assertEquals(1, result, "Counter should be 1");
    }
}