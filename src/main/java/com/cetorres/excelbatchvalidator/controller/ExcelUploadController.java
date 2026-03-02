package com.cetorres.excelbatchvalidator.controller;

import com.cetorres.excelbatchvalidator.domain.ValidationReport;
import static com.cetorres.excelbatchvalidator.dto.Result.*;
import com.cetorres.excelbatchvalidator.dto.Result;
import com.cetorres.excelbatchvalidator.service.ExcelDataValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file/")
public class ExcelUploadController {

    private final ExcelDataValidationService excelDataValidationService;

    public ExcelUploadController(ExcelDataValidationService excelDataValidationService) {
        this.excelDataValidationService = excelDataValidationService;
    }

    @PostMapping("upload")
    public ResponseEntity<Result<ValidationReport>> uploadExcel(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(success(excelDataValidationService.process(file)));
    }

}
