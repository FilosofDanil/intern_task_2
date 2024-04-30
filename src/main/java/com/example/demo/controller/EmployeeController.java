package com.example.demo.controller;

import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.dtos.Statistic;
import com.example.demo.services.CRUDService;
import com.example.demo.services.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    CRUDService<EmployeeDTO> employeeCrudService;

    EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeCrudService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeCrudService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
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
    public void getAllEmployeesRepost() {
        //TODO Implement report uploading
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
