package com.example.petstore.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {
    private long id;
    private String name;
    private String url;
    private int age;
    private String status;
}
