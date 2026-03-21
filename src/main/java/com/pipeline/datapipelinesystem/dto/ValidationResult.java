package com.pipeline.datapipelinesystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResult {

    private List<CustomerRecord> validRecords;
    private List<CustomerRecord> invalidRecords;

}
