package com.pipeline.datapipelinesystem.exception;

public class PipelineException extends RuntimeException{
    public PipelineException(String message){
        super(message);
    }
    public PipelineException(String message,Throwable msg){
        super(message,msg);
    }
}
