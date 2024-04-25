package com.example.demo.mappers;

import com.example.demo.dtos.CompanyDTO;
import com.example.demo.entities.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDto(Company company);

    Company toEntity(CompanyDTO dto);
}
