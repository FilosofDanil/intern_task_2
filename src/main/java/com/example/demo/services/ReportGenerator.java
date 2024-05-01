package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ReportGenerator {
    ByteArrayInputStream employeesToCsv() throws IOException;

    ByteArrayInputStream employeesToExcel() throws IOException;
}
