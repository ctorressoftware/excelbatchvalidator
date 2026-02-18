package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
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

    public List<Person> extractAndFormat(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new RuntimeException("Invalid excel file");

        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            final var dataFormatter = new DataFormatter(Locale.US);
            final XSSFSheet sheet = workbook.getSheet("Sheet1");
            final List<Person> persons = new ArrayList<>();
            sheet.removeRow(sheet.getRow(0));

            sheet.rowIterator().forEachRemaining(row ->
                    persons.add(Person.builder()
                            .dni(dataFormatter.formatCellValue(row.getCell(1)))
                            .firstName(row.getCell(2).getStringCellValue())
                            .secondName(row.getCell(3).getStringCellValue())
                            .firstLastname(row.getCell(4).getStringCellValue())
                            .secondLastname(row.getCell(5).getStringCellValue())
                            .gender(row.getCell(6).getStringCellValue().charAt(0))
                            .email(row.getCell(7).getStringCellValue())
                            .phoneNumber(dataFormatter.formatCellValue(row.getCell(8)))
                            .build())
            );

            return persons;
        } catch (IOException e) {
            throw new ExcelNotOpenException("Cannot open the document: " + e.getMessage());
        }
    }

}
