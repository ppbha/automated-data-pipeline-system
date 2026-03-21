package com.pipeline.datapipelinesystem.controller;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.service.DBLoaderService;
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
    private final DBLoaderService dbLoaderService;

    public FileTestController(FileReaderService fileReaderService,ValidationService validationService,DBLoaderService dbLoaderService){
        this.fileReaderService = fileReaderService;
        this.validationService = validationService;
        this.dbLoaderService = dbLoaderService;
    }

    @GetMapping("/read-file")
    public List<CustomerRecord> readFile(){
        return fileReaderService.readFile("input/customer-data.csv");
    }

    @GetMapping("/process-file")
    public String processFile(){
        List<CustomerRecord> records = fileReaderService.readFile("input/customer-data.csv");
        ValidationResult result = validationService.validate(records);
        dbLoaderService.loadToDatabase(result);
        return "File processed and stored in Database Successfully!";
    }


}
