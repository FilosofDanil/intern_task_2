package com.example.demo.controller;

import com.example.demo.dtos.Statistic;
import com.example.demo.dtos.employee.EmployeeCreationDTO;
import com.example.demo.dtos.employee.EmployeeDTO;
import com.example.demo.services.CRUDService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.ReportGenerator;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    CRUDService<EmployeeDTO> employeeCrudService;

    EmployeeService employeeService;

    ReportGenerator reportGenerator;

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeCrudService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeCrudService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeCreationDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeCrudService.create(employeeDTO));
    }

    @PostMapping("/upload")
    public ResponseEntity<Statistic> uploadEmployee(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(employeeService.uploadEmployeeJSON(file));
    }

    @GetMapping("/_list")
    public Page<EmployeeDTO> getAllEmployeesWithPagination(@RequestParam(required = false) Long companyId,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String surname,
                                                           @RequestParam(required = false) Long salaryFrom,
                                                           @RequestParam(required = false) Long salaryTo,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeService.getAllEmployeesWithPagination(companyId, name, surname, salaryFrom, salaryTo, pageable);
    }

    @GetMapping("/_report")
    public ResponseEntity<InputStreamResource> generateReport(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Long salaryFrom,
            @RequestParam(required = false) Long salaryTo) {
        String fileName = "employees_report.csv";
        ByteArrayInputStream in = reportGenerator.generateReport(companyId, name, surname, salaryFrom, salaryTo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") Long id,
                                               @RequestBody EmployeeDTO employeeDTO) {
        employeeCrudService.update(employeeDTO, id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeCrudService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
