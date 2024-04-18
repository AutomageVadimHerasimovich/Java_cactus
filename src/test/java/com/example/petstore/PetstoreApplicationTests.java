package com.example.petstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = PetstoreApplication.class)
class PetstoreApplicationTests {

    @Autowired
    private PetstoreApplication petstoreApplication;

    @Test
    void contextLoads() {
        assertNotNull(petstoreApplication);
    }
}