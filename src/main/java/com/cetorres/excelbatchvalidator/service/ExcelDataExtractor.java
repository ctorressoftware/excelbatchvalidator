package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.exception.ExcelNotOpenException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import static com.cetorres.excelbatchvalidator.enums.DataColumn.*;

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
                            .dni(dataFormatter.formatCellValue(row.getCell(DNI.getIndex())))
                            .firstName(row.getCell(FIRST_NAME.getIndex()).getStringCellValue())
                            .secondName(row.getCell(SECOND_NAME.getIndex()).getStringCellValue())
                            .firstLastname(row.getCell(FIRST_LASTNAME.getIndex()).getStringCellValue())
                            .secondLastname(row.getCell(SECOND_LASTNAME.getIndex()).getStringCellValue())
                            .gender(row.getCell(GENDER.getIndex()).getStringCellValue().charAt(0))
                            .email(row.getCell(EMAIL.getIndex()).getStringCellValue())
                            .phoneNumber(dataFormatter.formatCellValue(row.getCell(PHONE_NUMBER.getIndex())))
                            .build())
            );

            return persons;
        } catch (IOException e) {
            throw new ExcelNotOpenException("Cannot open the document: " + e.getMessage());
        }
    }

}
