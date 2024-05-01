package com.example.demo.controller;

import com.example.demo.dtos.company.CompanyCreationDTO;
import com.example.demo.dtos.company.CompanyDTO;
import com.example.demo.services.CRUDService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    CRUDService<CompanyDTO> companyCrudService;

    @GetMapping("")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyCrudService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(companyCrudService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyCreationDTO companyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyCrudService.create(companyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCompany(@PathVariable("id") Long id,
                                              @RequestBody CompanyDTO companyDTO) {
        companyCrudService.update(companyDTO, id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        companyCrudService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
