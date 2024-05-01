package com.example.demo.dtos.company;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyCreationDTO extends CompanyDTO {
    @NotBlank(message = "Name is mandatory")
    String name;
}
