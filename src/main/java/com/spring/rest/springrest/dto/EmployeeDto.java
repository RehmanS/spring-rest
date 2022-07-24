package com.spring.rest.springrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
    private int age;
    private double salary;
}
