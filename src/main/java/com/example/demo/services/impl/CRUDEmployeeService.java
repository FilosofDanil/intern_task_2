package com.example.demo.services.impl;

import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.CRUDService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CRUDEmployeeService implements CRUDService<EmployeeDTO> {
    EmployeeMapper employeeMapper;

    EmployeeRepository employeeRepository;

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
        employeeRepository.save(employeeMapper.toEntity(employeeDTO));
        return employeeDTO;
    }

    @Override
    public void update(EmployeeDTO employeeDTO, Long id) {
        //TODO Implement update method
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
