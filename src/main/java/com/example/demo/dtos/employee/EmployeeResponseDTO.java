package com.example.demo.dtos.employee;

import com.example.demo.dtos.company.CompanyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponseDTO extends EmployeeDTO {
    String selfLink;
}
