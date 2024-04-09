package com.example.petstore;

import com.example.petstore.exception.MyExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The main class of the application.
 */
@EnableCaching
@SpringBootApplication
@EnableWebMvc
@MyExceptionHandler
public class PetstoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(PetstoreApplication.class, args);
  }
}
