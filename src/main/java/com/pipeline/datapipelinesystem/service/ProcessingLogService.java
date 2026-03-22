package com.pipeline.datapipelinesystem.service;

import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.entity.FileProcessingLog;
import com.pipeline.datapipelinesystem.repository.FileProcessingLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessingLogService {
    private final FileProcessingLogRepository fileProcessingLogRepository;


    public ProcessingLogService(FileProcessingLogRepository fileProcessingLogRepository){
        this.fileProcessingLogRepository = fileProcessingLogRepository;
    }

    public void log(String fileName, ValidationResult result, String status){
        FileProcessingLog fileProcessingLog = new FileProcessingLog();
        fileProcessingLog.setFilename(fileName);
        fileProcessingLog.setTotalRecords(result.getInvalidRecords().size() +
                result.getValidRecords().size());
        fileProcessingLog.setSuccessRecords(result.getValidRecords().size());
        fileProcessingLog.setFailedRecords(result.getInvalidRecords().size());
        fileProcessingLog.setStatus(status);
        fileProcessingLog.setProcessingTime(LocalDateTime.now());

        fileProcessingLogRepository.save(fileProcessingLog);
    }

}
