package com.example.demo.repositories;

import com.example.demo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "    company_id = CASE WHEN :companyName IS NOT NULL" +
            " THEN (SELECT id FROM companies WHERE company_name = :companyName) ELSE company_id END " +
            "WHERE id = :id",
            nativeQuery = true)
    void updateEmployee(@Param("id") Long id,
                        @Param("name") String name,
                        @Param("surname") String surname,
                        @Param("salary") Integer salary,
                        @Param("hiringDate") LocalDate hiringDate,
                        @Param("job") String job,
                        @Param("companyName") String companyName);

    @Query(value = "WITH inserted_employee AS (" +
            "    INSERT INTO employees (id, employee_name, employee_surname, salary, hiring_date, job, company_id)" +
            "    VALUES" +
            "        (nextval('employee_seq'), :name, :surname, :salary, :hiringDate, :job, " +
            "(SELECT id FROM companies WHERE company_name = :companyName))" +
            "    RETURNING *" +
            ")" +
            "SELECT * FROM inserted_employee;", nativeQuery = true)
    Employee insertEmployee(@Param("name") String name,
                            @Param("surname") String surname,
                            @Param("salary") Integer salary,
                            @Param("hiringDate") LocalDate hiringDate,
                            @Param("job") String job,
                            @Param("companyName") String companyName);

    @Query("SELECT e FROM Employee e " +
            "WHERE (:companyId IS NULL OR e.company.id = :companyId) " +
            "AND (:name IS NULL OR e.name = :name) " +
            "AND (:surname IS NULL OR e.surname = :surname) " +
            "AND (:salary IS NULL OR e.salary = :salary)")
    Page<Employee> getAllEmployeePages(
            @Param("companyId") Long companyId,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("salary") Long salary,
            Pageable pageable
    );
}
