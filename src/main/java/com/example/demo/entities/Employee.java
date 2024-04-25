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
    @Column(name = "employee_name")
    String name;

    @Column(name = "employee_surname")
    String surname;

    @Column(name = "salary")
    Integer salary;

    @Column(name = "hiring_date")
    LocalDate hiringDate;

    @Column(name = "job")
    @Enumerated(EnumType.STRING)
    Jobs job;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    Company company;
}