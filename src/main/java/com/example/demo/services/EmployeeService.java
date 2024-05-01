package com.example.demo.services;

import com.example.demo.dtos.Statistic;
import com.example.demo.dtos.employee.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    Statistic uploadEmployeeJSON(MultipartFile file);

    Page<EmployeeDTO> getAllEmployeesWithPagination(Long companyId, String name,
                                                    String surname,  Long salary, Pageable pageable);
}
