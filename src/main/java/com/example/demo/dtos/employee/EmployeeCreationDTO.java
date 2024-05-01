package com.example.demo.dtos.employee;

import com.example.demo.dtos.company.CompanyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeCreationDTO extends EmployeeDTO{
    @NotBlank(message = "Name is mandatory")
    String name;
    @NotBlank(message = "Surname is mandatory")
    String surname;
    @NotBlank(message = "Salary is mandatory")
    String hiringDate;
    @NotBlank(message = "Job is mandatory")
    String job;
    @NotNull(message = "Company may not be null")
    CompanyDTO company;
}
