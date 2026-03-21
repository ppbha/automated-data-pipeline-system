package com.pipeline.datapipelinesystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "process_records")
public class ProcessedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer customerId;
    private String name;
    private Double amount;
}
