package com.pipeline.datapipelinesystem.service;

import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.dto.ValidationResult;
import com.pipeline.datapipelinesystem.exception.PipelineException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    public ValidationResult validate(List<CustomerRecord> records){

        List<CustomerRecord> validList = new ArrayList<>();
        List<CustomerRecord> invalidList = new ArrayList<>();

        for(CustomerRecord record : records){
            if(isValid(record)){
                validList.add(record);
            }else{
                invalidList.add(record);
            }
        }
        ValidationResult result = new ValidationResult();
        result.setValidRecords(validList);
        result.setInvalidRecords(invalidList);
        return result;
    }
    private boolean isValid(CustomerRecord record){
        if(record.getCustomerId() == null)
            throw new PipelineException("customer ID is missing");
        if(record.getName() == null || record.getName().isEmpty())
            throw new PipelineException("Customer Name is missing");
        if(record.getAmount() == null || record.getAmount() <= 0)
            throw new PipelineException("Amount is missing");

        return true;
    }
}
