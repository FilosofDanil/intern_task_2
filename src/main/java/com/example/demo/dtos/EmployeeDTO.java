package com.example.demo.dtos;

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
    @NotBlank(message = "Name is mandatory")
    String name;
    @NotBlank(message = "Surname is mandatory")
    String surname;
    @NotBlank(message = "Salary is mandatory")
    String salary;
    String hiringDate;
    @NotBlank(message = "Job is mandatory")
    String job;
    @NotNull(message = "Company may not be null")
    CompanyDTO company;
    String selfLink;
}
