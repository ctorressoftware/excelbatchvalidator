package com.cetorres.excelbatchvalidator.controller;

import com.cetorres.excelbatchvalidator.service.ExcelReader;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("file/")
public class ExcelUploadController {

    private final ExcelReader excelReader;

    public ExcelUploadController(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }

    @PostMapping("upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        return excelReader.read(file);
    }

}
