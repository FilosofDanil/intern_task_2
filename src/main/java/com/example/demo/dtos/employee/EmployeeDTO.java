package com.example.demo.dtos.employee;

import com.example.demo.dtos.company.CompanyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    String name;
    String surname;
    String salary;
    String hiringDate;
    String job;
    CompanyDTO company;
}
