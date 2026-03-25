package com.pipeline.datapipelinesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DataPipelineSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataPipelineSystemApplication.class, args);
    }

}
