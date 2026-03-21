package com.pipeline.datapipelinesystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "error_records")
public class ErrorRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer customerId;
    private String name;
    private Double amount;
    private String errorMessage;
}
