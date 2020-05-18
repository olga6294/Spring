package com.example.demo.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DropboxConfiguration {

    private DbxRequestConfig config;

    @Autowired
    private Environment environment;

    @Bean
    public DbxClientV2 dropboxClient(){
        config = new DbxRequestConfig("dropbox/java-tutorial");
         return new DbxClientV2(config, environment.getProperty("dropbox.accessKey"));
    }
}
