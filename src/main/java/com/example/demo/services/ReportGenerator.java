package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ReportGenerator {
    ByteArrayInputStream generateReport(Long companyId, String name,
                                        String surname,  Long salaryFrom, Long salaryTo);
}
