package com.pipeline.datapipelinesystem.service;

import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileMoveService {

    public void moveFile(String sourcePath,String targetPath){
        try{
            Files.move(
                    Path.of(sourcePath),
                    Path.of(targetPath),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
