package com.example.demo.services;

import com.example.demo.dtos.Statistic;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    Statistic uploadEmployeeJSON(MultipartFile file);
}
