package com.example.demo.mappers;

import com.example.demo.dtos.employee.EmployeeDTO;
import com.example.demo.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", implementationName = "employeeMapper")
public interface EmployeeMapper {

    String api = "http://localhost:8080/api/employee/";
    @Mapping(target = "selfLink", source = "employee.id", qualifiedByName = "idToLink")
    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO dto);

    @Named("idToLink")
    default String idToLink(Long id){
        return api +id;
    }
}
