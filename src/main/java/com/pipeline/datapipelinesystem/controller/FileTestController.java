package com.pipeline.datapipelinesystem.controller;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.service.FileReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.List;

@RestController
public class FileTestController {

    private final FileReaderService fileReaderService;

    public FileTestController(FileReaderService fileReaderService){
        this.fileReaderService = fileReaderService;
    }

    @GetMapping("/read-file")
    public List<CustomerRecord> readFile(){
        return fileReaderService.readFile("input/customer-data.csv");
    }


}
