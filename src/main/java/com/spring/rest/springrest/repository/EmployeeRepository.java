package com.spring.rest.springrest.repository;

import com.spring.rest.springrest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByNameAndSurname(String name,String surname);
}
