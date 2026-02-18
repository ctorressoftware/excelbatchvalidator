package com.cetorres.excelbatchvalidator.controller;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.service.ExcelDataExtractor;
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

    public ExcelUploadController(ExcelDataExtractor excelDataExtractor) {
        this.excelDataExtractor = excelDataExtractor;
    }

    @PostMapping("upload")
    public List<Person> uploadExcel(@RequestParam("file") MultipartFile file) {
        return excelDataExtractor.extractAndFormat(file);
    }

}
