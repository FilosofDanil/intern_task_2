package com.example.demo.controller;

import com.example.demo.dtos.CompanyDTO;
import com.example.demo.dtos.EmployeeDTO;
import com.example.demo.services.CRUDService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    CRUDService<EmployeeDTO> employeeCrudService;

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
