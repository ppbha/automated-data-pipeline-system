package com.pipeline.datapipelinesystem.repository;

import com.pipeline.datapipelinesystem.entity.ErrorRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRecordRepository extends JpaRepository<ErrorRecord,Long> {
}
