package com.spring.rest.springrest.controller;

import com.spring.rest.springrest.dto.EmployeeDto;
import com.spring.rest.springrest.response.EmployeeResponse;
import com.spring.rest.springrest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Tag(name="Employee Services")  // swagger
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/allEmployees")
    @Operation(summary = "Get All Employees") // swagger
    public EmployeeResponse getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{emp-id}")
    public EmployeeDto getEmployee(@PathVariable("emp-id") int id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/search")
    public EmployeeResponse findByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname) {

        return employeeService.findByNameAndSurname(name, surname);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // status kod qaytarmaq üçün
    public void insert(@Valid  @RequestBody EmployeeDto employeeDto) { // @Valid validation üçün
        employeeService.insert(employeeDto);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Method security
    public void update(@RequestBody EmployeeDto employeeDto, @PathVariable("id") int id){
        employeeService.update(employeeDto,id);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')") // Method security
    public void updateSome(@RequestBody EmployeeDto employeeDto, @PathVariable("id") int id){
        employeeService.updateSome(employeeDto,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        employeeService.delete(id);
    }


}
