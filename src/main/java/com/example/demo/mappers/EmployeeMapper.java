package com.example.demo.mappers;

import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "employeeMapper")
public interface EmployeeMapper {
    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO dto);
}