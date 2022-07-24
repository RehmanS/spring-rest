package com.spring.rest.springrest.service;

import com.spring.rest.springrest.dto.EmployeeDto;
import com.spring.rest.springrest.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();

    EmployeeDto getEmployee(int id);

    EmployeeResponse findByNameAndSurname(String name, String surname);

    void insert(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto,int id);

    void updateSome(EmployeeDto employeeDto, int id);

    void delete(int id);
}
