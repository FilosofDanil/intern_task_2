package com.example.demo.repositories;

import com.example.demo.entities.Employee;
import com.example.demo.enums.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Query(value = "UPDATE employees SET employee_name = CASE WHEN :name IS NOT NULL THEN :name ELSE employee_name END," +
            "    employee_surname = CASE WHEN :surname IS NOT NULL THEN :surname ELSE employee_surname END," +
            "    salary = CASE WHEN :salary IS NOT NULL THEN :salary ELSE salary END," +
            "    hiring_date = CASE WHEN :hiringDate IS NOT NULL THEN :hiringDate ELSE hiring_date END," +
            "    job = CASE WHEN :job IS NOT NULL THEN :job ELSE job END," +
            "    company_id = CASE WHEN :companyId IS NOT NULL THEN :companyId ELSE company_id END " +
            "WHERE id = :id",
            nativeQuery = true)
    void updateEmployee(@Param("id") Long id,
                        @Param("name") String name,
                        @Param("surname") String surname,
                        @Param("salary") Integer salary,
                        @Param("hiringDate") LocalDate hiringDate,
                        @Param("job") String job,
                        @Param("companyId") Long companyId);
}
