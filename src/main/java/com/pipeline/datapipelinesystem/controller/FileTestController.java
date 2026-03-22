package com.pipeline.datapipelinesystem.controller;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.service.DBLoaderService;
import com.pipeline.datapipelinesystem.service.FileReaderService;
import com.pipeline.datapipelinesystem.service.ProcessingLogService;
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
    private final ProcessingLogService processingLogService;

    public FileTestController(FileReaderService fileReaderService,ValidationService validationService,DBLoaderService dbLoaderService,ProcessingLogService processingLogService){
        this.fileReaderService = fileReaderService;
        this.validationService = validationService;
        this.dbLoaderService = dbLoaderService;
        this.processingLogService = processingLogService;
    }

    @GetMapping("/read-file")
    public List<CustomerRecord> readFile(){
        return fileReaderService.readFile("input/customer-data.csv");
    }

    @GetMapping("/process-file")
    public String processFile(){
        String fileName = "customer-data.csv";
        try{
            List<CustomerRecord> records = fileReaderService.readFile("input/"+ fileName);
            ValidationResult result = validationService.validate(records);
            dbLoaderService.loadToDatabase(result);
            processingLogService.log(fileName,result,"SUCCESS");
            return "File processed and stored in Database Successfully!";
        } catch (Exception e) {
            processingLogService.log(fileName, new ValidationResult(),"FAILED");
            return "File processing Failed!";
        }
    }


}
