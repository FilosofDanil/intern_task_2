package com.example.demo.controller;

import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.dtos.Statistic;
import com.example.demo.entities.Employee;
import com.example.demo.services.CRUDService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.ReportGenerator;
import com.example.demo.services.impl.EmployeeServiceImpl;
import com.example.demo.services.impl.RepostGeneratorImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeCrudService.create(employeeDTO));
    }

    @PostMapping("/upload")
    public ResponseEntity<Statistic> uploadEmployee(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(employeeService.uploadEmployeeJSON(file));
    }

    @GetMapping("/_list")
    public void getAllEmployeesWithPagination() {
        //TODO Implement list with filters and pagination
    }

    @GetMapping("/_report")
    public ResponseEntity<InputStreamResource> generateReport(
            @RequestParam(name = "format", required = false) String format,
            @RequestParam(name = "entity2Id", required = false) Long entity2Id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname) {
        ByteArrayInputStream in = null;
        String fileName = "employees_report";
        if (format.equals("excel")){
            fileName += ".excel";
            try {
                in = reportGenerator.employeesToCsv();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileName += ".csv";
            try {
                in = reportGenerator.employeesToExcel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
