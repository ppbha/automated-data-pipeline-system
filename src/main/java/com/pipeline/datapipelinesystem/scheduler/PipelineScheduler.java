package com.pipeline.datapipelinesystem.scheduler;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.service.DBLoaderService;
import com.pipeline.datapipelinesystem.service.FileMoveService;
import com.pipeline.datapipelinesystem.service.FileReaderService;
import com.pipeline.datapipelinesystem.service.ValidationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class PipelineScheduler {

    private final DBLoaderService dbLoaderService;
    private final FileReaderService fileReaderService;
    private final ValidationService validationService;
    private final FileMoveService fileMoveService;

    public PipelineScheduler(DBLoaderService dbLoaderService, FileReaderService fileReaderService, ValidationService validationService, FileMoveService fileMoveService){
        this.dbLoaderService = dbLoaderService;
        this.fileReaderService = fileReaderService;
        this.validationService = validationService;
        this.fileMoveService = fileMoveService;
    }

    @Scheduled(fixedRate = 30000)
    public void runPipeline(){
        String sourceFilePath = "input/customer-data.csv";
        String targetFilePath = "processed/customer-data.csv";
        File file = new File(sourceFilePath);
        if(!file.exists()){
            System.out.println("No file found to process");
            return;
        }
        System.out.println("Processing File......");
        List<CustomerRecord> records = fileReaderService.readFile(sourceFilePath);
        ValidationResult result =
                validationService.validate(records);
        dbLoaderService.loadToDatabase(result);
        fileMoveService.moveFile(sourceFilePath,targetFilePath);
        System.out.println("File Moved successfully!");
    }
}
