package com.pipeline.datapipelinesystem.service;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.entity.ErrorRecord;
import com.pipeline.datapipelinesystem.entity.ProcessedRecord;
import com.pipeline.datapipelinesystem.repository.ErrorRecordRepository;
import com.pipeline.datapipelinesystem.repository.ProcessedrecordRepository;
import org.springframework.stereotype.Service;

@Service
public class DBLoaderService {

    private final ProcessedrecordRepository processedrecordRepository;
    private final ErrorRecordRepository errorRecordRepository;

    public DBLoaderService(ProcessedrecordRepository processedrecordRepository,ErrorRecordRepository errorRecordRepository){
        this.processedrecordRepository = processedrecordRepository;
        this.errorRecordRepository = errorRecordRepository;
    }

    public void loadToDatabase(ValidationResult result){
        //to save valid records
        for(CustomerRecord record : result.getValidRecords()){
            ProcessedRecord processedRecord = new ProcessedRecord();
            processedRecord.setCustomerId(record.getCustomerId());
            processedRecord.setName(record.getName());
            processedRecord.setAmount(record.getAmount());

            processedrecordRepository.save(processedRecord);
        }

        //to save Invalid records
        for(CustomerRecord record : result.getInvalidRecords()){
            ErrorRecord errorRecord = new ErrorRecord();
            errorRecord.setCustomerId(record.getCustomerId());
            errorRecord.setName(record.getName());
            errorRecord.setAmount(record.getAmount());
            errorRecord.setErrorMessage("Invalid data");

            errorRecordRepository.save(errorRecord);
        }
    }
}
