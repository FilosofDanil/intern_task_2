package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends BaseEntity {
    @Column(name = "company_name", unique = true)
    String name;

    @Column(name = "country")
    String country;

    @Column(name = "foundation_date")
    LocalDate foundationDate;

    @OneToMany(mappedBy = "company", orphanRemoval = true)
    List<Employee> employees;
}
