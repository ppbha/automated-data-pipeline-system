package com.pipeline.datapipelinesystem.scheduler;

import com.pipeline.datapipelinesystem.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class PipelineScheduler {

    private final PipelineService pipelineService;
    private static final Logger log = LoggerFactory.getLogger(PipelineScheduler.class);

    public PipelineScheduler(PipelineService pipelineService){
        this.pipelineService = pipelineService;
    }

    @Scheduled(fixedRate = 30000)
    public void runPipeline(){
        log.info("Running Pipeline..........");
      String file = "input/customer-data.csv";
      pipelineService.processFile(file);
    }
}
