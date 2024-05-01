package com.example.demo.entities;


import com.example.demo.enums.Jobs;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends BaseEntity {
    @Column(name = "employee_name", nullable = false)
    String name;

    @Column(name = "employee_surname", nullable = false)
    String surname;

    @Column(name = "salary", nullable = false)
    Integer salary;

    @Column(name = "hiring_date")
    LocalDate hiringDate;

    @Column(name = "job", nullable = false)
    @Enumerated(EnumType.STRING)
    Jobs job;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    Company company;
}
