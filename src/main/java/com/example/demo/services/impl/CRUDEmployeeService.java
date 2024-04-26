package com.example.demo.services.impl;

import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.entities.Company;
import com.example.demo.entities.Employee;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.EmployeeRepository;
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
public class CRUDEmployeeService implements CRUDService<EmployeeDTO> {
    EmployeeMapper employeeMapper;

    EmployeeRepository employeeRepository;

    CompanyRepository companyRepository;

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDTO getById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(employeeMapper::toDto).get();
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Optional<Company> company = companyRepository.findByName(employeeDTO.getCompany().getName());
        employee.setCompany(company.get());
        employeeRepository.save(employee);
        return employeeDTO;
    }

    @Transactional
    @Override
    public void update(EmployeeDTO employeeDTO, Long id) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Optional<Long> companyId = Optional.empty();
        if(employeeDTO.getCompany()!=null){
            Optional<Company> company = companyRepository.findByName(employeeDTO.getCompany().getName());
            if (company.isPresent()) {
                companyId = Optional.of(company.get().getId());
            }
        }
        employeeRepository.updateEmployee(id, employee.getName(), employee.getSurname(),
                employee.getSalary(), employee.getHiringDate(), employeeDTO.getJob(), companyId.orElse(null));
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
