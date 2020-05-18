package com.example.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Keys {

    private final Environment environment;

    public String getDropboxAccessKey(){
        return environment.getProperty("dropbox.accessKey");
    }

    public String getListeningDirectory(){
        return environment.getProperty("listening.directory");
    }
}
