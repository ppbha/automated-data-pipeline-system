package com.pipeline.datapipelinesystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "file_processing_log")
public class FileProcessingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private Integer totalRecords;
    private Integer successRecords;
    private Integer failedRecords;

    private String status;
    private LocalDateTime processingTime;
}
