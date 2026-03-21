package com.pipeline.datapipelinesystem.controller;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.service.FileReaderService;
import com.pipeline.datapipelinesystem.service.ValidationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.List;

@RestController
public class FileTestController {

    private final FileReaderService fileReaderService;
    private final ValidationService validationService;

    public FileTestController(FileReaderService fileReaderService,ValidationService validationService){
        this.fileReaderService = fileReaderService;
        this.validationService = validationService;
    }

    @GetMapping("/read-file")
    public List<CustomerRecord> readFile(){
        return fileReaderService.readFile("input/customer-data.csv");
    }

    @GetMapping("/process-file")
    public ValidationResult processFile(){
        List<CustomerRecord> records = fileReaderService.readFile("input/customer-data.csv");
        return validationService.validate(records);
    }


}
