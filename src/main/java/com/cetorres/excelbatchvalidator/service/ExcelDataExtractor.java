package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.exception.ExcelNotOpenException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ExcelDataExtractor {

    public List<List<String>> extract(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new RuntimeException("Invalid excel file");

        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            final var dataFormatter = new DataFormatter(Locale.US);
            final XSSFSheet sheet = workbook.getSheet("Sheet1");
            final List<List<String>> dataRows = new ArrayList<>(sheet.getLastRowNum());
            sheet.removeRow(sheet.getRow(0));

            sheet.rowIterator().forEachRemaining(row -> {

                final List<String> cellsData = new ArrayList<>(row.getLastCellNum());

                row.cellIterator().forEachRemaining(cell -> {
                    cellsData.add(dataFormatter.formatCellValue(cell));
                });

                dataRows.add(cellsData);
            });
            return dataRows;

        } catch (IOException e) {
            throw new ExcelNotOpenException("Cannot open the document: " + e.getMessage());
        }
    }
}
