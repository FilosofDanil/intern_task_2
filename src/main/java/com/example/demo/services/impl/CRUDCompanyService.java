package com.example.demo.services.impl;

import com.example.demo.dtos.CompanyDTO;
import com.example.demo.entities.Company;
import com.example.demo.entities.Employee;
import com.example.demo.mappers.CompanyMapper;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.services.CRUDService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CRUDCompanyService implements CRUDService<CompanyDTO> {
    CompanyMapper companyMapper;

    CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDto)
                .toList();
    }

    @Override
    public CompanyDTO getById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(companyMapper::toDto).get();
    }

    @Override
    public CompanyDTO create(CompanyDTO companyDTO) {
        companyRepository.save(companyMapper.toEntity(companyDTO));
        return companyDTO;
    }

    @Transactional
    @Override
    public void update(CompanyDTO companyDTO, Long id) {
        Company company= companyMapper.toEntity(companyDTO);
        companyRepository.updateCompany(id, company.getName(),
                company.getCountry(), company.getFoundationDate());
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
