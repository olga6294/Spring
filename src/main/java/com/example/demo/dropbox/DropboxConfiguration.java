package com.example.demo.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.example.demo.config.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DropboxConfiguration {

    private DbxRequestConfig config;
    private final Keys keys;

    @Bean
    public DbxClientV2 dropboxClient(){
        config = new DbxRequestConfig("dropbox/java-tutorial");
         return new DbxClientV2(config, keys.getDropboxAccessKey());
    }
}
