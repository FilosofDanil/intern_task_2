package com.example.demo.services.impl;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.ReportGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RepostGeneratorImpl implements ReportGenerator {
    EmployeeRepository employeeRepository;

    public ByteArrayInputStream employeesToCsv() throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(CSVFormat.EXCEL.getQuoteMode());
        List<Employee> employees = employeeRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(out, StandardCharsets.UTF_8), format)) {
            csvPrinter.printRecord("Name", "Surname", "Salary", "Hiring Date", "Job", "Company");

            for (Employee employee : employees) {
                csvPrinter.printRecord(
                        employee.getName(),
                        employee.getSurname(),
                        employee.getSalary(),
                        employee.getHiringDate(),
                        employee.getJob(),
                        employee.getCompany().getName());
            }

            csvPrinter.flush();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream employeesToExcel() throws IOException {
        List<Employee> employees = employeeRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Name", "Surname", "Salary", "Hiring Date", "Job", "Company"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(employee.getName());
                row.createCell(1).setCellValue(employee.getSurname());
                row.createCell(2).setCellValue(employee.getSalary());
                row.createCell(3).setCellValue(employee.getHiringDate().toString());
                row.createCell(4).setCellValue(employee.getJob().toString());
                row.createCell(5).setCellValue(employee.getCompany().getName());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
