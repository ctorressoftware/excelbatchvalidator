package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.exception.ExcelNotOpenException;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
public class ExcelReader {

    public String read(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Invalid excel file");
        }

        try (var workbook = new XSSFWorkbook(file.getInputStream())) {

            var sheet = workbook.getSheet("Sheet1");

            sheet.rowIterator().forEachRemaining(row -> {
                row.cellIterator().forEachRemaining(x -> {
                    if (x.getCellType().equals(CellType.NUMERIC)) {
                        System.out.println(x.getNumericCellValue());
                    } else {
                        System.out.println(x.getStringCellValue());
                    }
                });
            });

            return "hola hola";
        } catch (IOException e) {
            throw new ExcelNotOpenException("Cannot open the document: " + e.getMessage());
        }
    }

}
