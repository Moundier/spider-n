package com.example.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Address {
  
    // private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    private String institution;
    private String campus; // ?
}
