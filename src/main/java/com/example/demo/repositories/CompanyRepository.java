package com.example.demo.repositories;

import com.example.demo.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);

    @Modifying
    @Query(value = "UPDATE companies SET company_name = CASE WHEN :name IS NOT NULL THEN :name ELSE company_name END," +
            "    country = CASE WHEN :country IS NOT NULL THEN :country ELSE country END," +
            "    foundation_date = CASE WHEN :foundationDate IS NOT NULL THEN :foundationDate ELSE foundation_date END " +
            "WHERE id = :id",
            nativeQuery = true)
    void updateCompany(@Param("id") Long id,
                       @Param("name") String name,
                       @Param("country") String country,
                       @Param("foundationDate") LocalDate foundationDate);

}
