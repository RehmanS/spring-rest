package com.spring.rest.springrest.serviceimpl;

import com.spring.rest.springrest.dto.EmployeeDto;
import com.spring.rest.springrest.enums.ErrorCodeEnum;
import com.spring.rest.springrest.exception.CustomException;
import com.spring.rest.springrest.model.Employee;
import com.spring.rest.springrest.repository.EmployeeRepository;
import com.spring.rest.springrest.response.EmployeeResponse;
import com.spring.rest.springrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeRepository.findAll()
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());

        return EmployeeResponse.builder()
                .employees(employeeDtoList)
                .build();
    }

    @Override
    public EmployeeDto getEmployee(int id) {
        return employeeRepository.findById(id)
                .map(employee -> convertToDto(employee))
                .orElseThrow(() -> new CustomException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public EmployeeResponse findByNameAndSurname(String name, String surname) {
        List<EmployeeDto> employees = employeeRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(employee -> convertToDto(employee))
                .collect(Collectors.toList());

        return EmployeeResponse.builder()
                .employees(employees)
                .build();
    }

    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto, int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));

        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));

        if (employeeDto.getName()!=null)
            employee.setName(employeeDto.getName());
        if (employeeDto.getSurname()!=null)
            employee.setSurname(employeeDto.getSurname());
        if (employeeDto.getAge()>0)
            employee.setAge(employeeDto.getAge());
        if (employeeDto.getSalary()>0)
            employee.setSalary(employeeDto.getSalary());

        employeeRepository.save(employee);

    }

    @Override
    public void delete(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto); // hər iki classdakı field adları eynidirsə(entity ve dto)
        return employeeDto;
    }
}
