package com.spring.rest.springrest.response;

import com.spring.rest.springrest.dto.EmployeeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private List<EmployeeDto> employees;

}
