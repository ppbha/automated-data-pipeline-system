package com.pipeline.datapipelinesystem.repository;

import com.pipeline.datapipelinesystem.entity.FileProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileProcessingLogRepository extends JpaRepository<FileProcessingLog,Long> {
}
