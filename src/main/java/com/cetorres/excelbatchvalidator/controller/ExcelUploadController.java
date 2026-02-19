package com.cetorres.excelbatchvalidator.controller;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.service.ExcelDataExtractor;
import com.cetorres.excelbatchvalidator.service.ExcelDataParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("file/")
public class ExcelUploadController {

    private final ExcelDataExtractor excelDataExtractor;
    private final ExcelDataParser excelDataParser;

    public ExcelUploadController(ExcelDataExtractor excelDataExtractor, ExcelDataParser excelDataParser) {
        this.excelDataExtractor = excelDataExtractor;
        this.excelDataParser = excelDataParser;
    }

    @PostMapping("upload")
    public List<Person> uploadExcel(@RequestParam("file") MultipartFile file) {
        var raw = excelDataExtractor.extract(file);
        return excelDataParser.transform(raw);
    }

}
