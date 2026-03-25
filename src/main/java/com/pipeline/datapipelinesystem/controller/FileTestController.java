package com.pipeline.datapipelinesystem.controller;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.List;

@RestController
public class FileTestController {

    private final FileReaderService fileReaderService;
    private final PipelineService pipelineService;

    public FileTestController(FileReaderService fileReaderService,PipelineService pipelineService){
        this.fileReaderService = fileReaderService;
        this.pipelineService = pipelineService;
    }

    @GetMapping("/read-file")
    public List<CustomerRecord> readFile(){
        return fileReaderService.readFile("input/customer-data.csv");
    }

    @GetMapping("/process-file")
    public String processFile(){
        String fileName = "customer-data.csv";
       pipelineService.processFile(fileName);
       return "Pipeline executed successfully!";
    }


}
