package com.example.demo.mappers;

import com.example.demo.dtos.company.CompanyDTO;
import com.example.demo.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", implementationName = "companyMapper")
public interface CompanyMapper {
    String api = "http://localhost:8080/api/employee/";

    @Mapping(target = "selfLink", source = "company.id", qualifiedByName = "idToLink")
    CompanyDTO toDto(Company company);

    Company toEntity(CompanyDTO dto);

    @Named("idToLink")
    default String idToLink(Long id){
        return api +id;
    }
}
