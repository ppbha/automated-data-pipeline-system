package com.pipeline.datapipelinesystem.repository;

import com.pipeline.datapipelinesystem.entity.ProcessedRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedrecordRepository extends JpaRepository<ProcessedRecord,Long> {
}
