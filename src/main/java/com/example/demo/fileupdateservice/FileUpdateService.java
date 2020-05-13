package com.example.demo.fileupdateservice;

import com.example.demo.file.FileController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;

@AllArgsConstructor
public class FileUpdateService {

    @Autowired
    private final Environment environment;
    @Autowired
    private FileController fileController;

    public void updateFiles(List<String> localDirectoryContent, List<String> dropboxDirectoryContent){
        if(localDirectoryContent.remove(dropboxDirectoryContent)){
            for(String file : localDirectoryContent){
                fileController.uploadFile(file, environment.getProperty("directory"));
            }
        }
    }
}