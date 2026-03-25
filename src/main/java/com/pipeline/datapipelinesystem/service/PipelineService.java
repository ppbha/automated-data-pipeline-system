package com.pipeline.datapipelinesystem.service;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.exception.PipelineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PipelineService {
    private final FileMoveService fileMoveService;
    private final FileReaderService fileReaderService;
    private final ValidationService validationService;
    private final DBLoaderService dbLoaderService;
    private static final Logger log = LoggerFactory.getLogger(PipelineService.class);

    public PipelineService(FileReaderService fileReaderService,FileMoveService fileMoveService, ValidationService validationService,DBLoaderService dbLoaderService){
        this.dbLoaderService = dbLoaderService;
        this.fileMoveService = fileMoveService;
        this.fileReaderService = fileReaderService;
        this.validationService = validationService;
    }
    public void processFile(String filepath){
        File file  = new File(filepath);
        if(!file.exists()){
            throw new PipelineException("File Not found" + filepath);
        }
        try {
            log.info("reading file...");
            List<CustomerRecord> records = fileReaderService.readFile(filepath);
            log.info("validating records...");
            ValidationResult result = validationService.validate(records);
            log.info("saving data to database...");
            dbLoaderService.loadToDatabase(result);
            fileMoveService.moveFile(filepath, "processed/" + file.getName());
            log.info("Pipeline completed successfully.!");
        } catch (Exception e) {
            log.error("Error occurred while processing file",e);
        }
    }
}
