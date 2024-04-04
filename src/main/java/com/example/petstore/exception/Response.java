package com.example.petstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the response object for exceptions.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
  private String message;
  private String description;
}