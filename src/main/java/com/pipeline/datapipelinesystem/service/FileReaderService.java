package com.pipeline.datapipelinesystem.service;


import com.pipeline.datapipelinesystem.dto.CustomerRecord;
import com.pipeline.datapipelinesystem.exception.PipelineException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileReaderService {

    public List<CustomerRecord> readFile(String filePath){
        List<CustomerRecord> records = new ArrayList<>();

        try{
            Reader reader = new FileReader(filePath);
            CSVParser csvparser = new CSVParser(reader,
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withIgnoreHeaderCase()
                            .withTrim());

            for(CSVRecord csvRecord: csvparser){
                CustomerRecord record = new CustomerRecord();
                record.setCustomerId(Integer.parseInt(csvRecord.get("customer_id")));
                record.setName(csvRecord.get("name"));
                record.setAmount(Double.parseDouble(csvRecord.get("amount")));
            records.add(record);
            }

        } catch (IOException e) {
            throw new PipelineException("Error reading file",e);
        }
        return records;
    }

}
